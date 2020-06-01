package edu.viu.securecoding.DevSecOps.controller;


import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
public class ImageControllerImpl implements ImageController {


    @Override
    @GetMapping("/image")
    public ResponseEntity<byte[]> getImage(final @RequestParam String imageName) throws IOException {

        File file = new File("src/main/resources/" + imageName);
        final byte[] bytes = Files.readAllBytes(file.toPath());

        return ResponseEntity
                .ok()
//                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);

//        final ClassPathResource imageFile = new ClassPathResource(imageName);
//        final byte[] bytes = StreamUtils.copyToByteArray(imageFile.getInputStream());
//
//        return ResponseEntity
//                .ok()
//                .contentType(MediaType.IMAGE_JPEG)
//                .body(bytes);
    }


    @PostMapping("/xml")
    public ResponseEntity<String> uploadXml(final @RequestBody String xml) throws XMLStreamException {


        XMLInputFactory factory = XMLInputFactory.newFactory();

        XMLStreamReader reader = factory.createXMLStreamReader(new ByteArrayInputStream(xml.getBytes()));
//        factory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false);
//        factory.setProperty(XMLInputFactory.SUPPORT_DTD, false);
        return ResponseEntity.ok().body("All good");
    }

}
