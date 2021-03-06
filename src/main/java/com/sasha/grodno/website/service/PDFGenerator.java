package com.sasha.grodno.website.service;

import com.lowagie.text.DocumentException;
import com.sasha.grodno.website.model.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class PDFGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger(PDFGenerator.class);

    @Autowired
    private TemplateEngine templateEngine;


    public void createPDF(String templateName, String nameFile, Ticket ticket) {
        Context context = new Context();
        context.setVariable("ticket", ticket);

        String html = templateEngine.process(templateName, context);
        String outputFileName = System.getProperty("user.home") + File.separator + nameFile + ".pdf";

        try (FileOutputStream outputStream = new FileOutputStream(outputFileName)) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(html);
            renderer.layout();
            renderer.createPDF(outputStream);
            renderer.finishPDF();
        } catch (IOException | DocumentException e) {
            LOGGER.error("Exception occurred.", e);
        }
    }

}
