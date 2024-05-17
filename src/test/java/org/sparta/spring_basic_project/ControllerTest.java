package org.sparta.spring_basic_project;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.sparta.spring_basic_project.controller.FileController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.ServletContext;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
public class ControllerTest {
    @InjectMocks
    private FileController fileController;

    @Mock
    private MultipartFile uploadFile;

    @Mock
    private ServletContext servletContext;
    @Mock
    private HttpServletRequest req;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFileUploadSuccess() throws IOException {
        String filename = "test.jpg";
        String filepath = "/Users/hyunacho/Downloads/" + filename;

        when(uploadFile.getOriginalFilename()).thenReturn(filename);
        when(uploadFile.getBytes()).thenReturn("test content".getBytes());

        String result = fileController.fileUpload(uploadFile, req);

        assertEquals("file upload success", result);
        File file = new File(filepath);
        assertTrue(file.exists());

        // Clean up test file
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testFileUploadInvalidExtension() {
        String filename = "test.txt";

        when(uploadFile.getOriginalFilename()).thenReturn(filename);

        String result = fileController.fileUpload(uploadFile, req);

        assertEquals("Only jpg, jpeg, png, gif files are allowed to upload.", result);
    }

    @Test
    public void testFileUploadException() throws IOException {
        String filename = "test.jpg";

        when(uploadFile.getOriginalFilename()).thenReturn(filename);
        when(uploadFile.getBytes()).thenThrow(new IOException("Mocked IO Exception"));

        String result = fileController.fileUpload(uploadFile, req);

        assertEquals("file upload fail", result);
    }

    @Test
    public void testDownloadSuccess() throws Exception {
        String filename = "test.jpg";
        String path = "/Users/hyunacho/Downloads";
        File file = new File(path, filename);
        file.createNewFile(); // Create a new file for testing
        file.deleteOnExit(); // Ensure the file is deleted after the test

        // Write some content to the file
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write("test content".getBytes());
        }

        MediaType mediaType = MediaType.IMAGE_JPEG;
        when(servletContext.getMimeType(filename)).thenReturn(mediaType.toString());

        ResponseEntity<InputStreamResource> response = fileController.download(filename, req);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("attachment; filename=\"" + filename + "\"", response.getHeaders().getContentDisposition().toString());
        assertEquals(mediaType, response.getHeaders().getContentType());
        assertEquals(file.length(), response.getHeaders().getContentLength());

        InputStreamResource isr = response.getBody();
        assertNotNull(isr);
        try (InputStream inputStream = isr.getInputStream()) {
            byte[] content = new byte[(int) file.length()];
            int bytesRead = inputStream.read(content);
            assertEquals("test content", new String(content, 0, bytesRead));
        }
    }
    @Test
    public void testDownloadFileNotFound() {
        String filename = "nonexistent.jpg";
        String path = "/Users/hyunacho/Downloads";
        when(servletContext.getMimeType(filename)).thenReturn(MediaType.APPLICATION_OCTET_STREAM.toString());

        Exception exception = assertThrows(FileNotFoundException.class, () -> {
            fileController.download(filename, req);
        });

        assertEquals(path+"/nonexistent.jpg (No such file or directory)", exception.getMessage());
    }
}
