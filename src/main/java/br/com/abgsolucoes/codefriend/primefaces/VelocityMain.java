package br.com.abgsolucoes.codefriend.primefaces;

import br.com.abgsolucoes.codefriend.primefaces.enums.ConfigEnum;
import br.com.abgsolucoes.codefriend.primefaces.enums.TemplatesUtilEnum;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.*;
import java.util.*;

/**
 * Created by adrian on 12/11/15.
 */
public class VelocityMain {


    private Log LOG = LogFactory.getLog(VelocityMain.class);


    private ConfigAbg configAbg;
    private VelocityEngine ve = new VelocityEngine();
    private VelocityContext context = new VelocityContext();

    public ConfigAbg getConfigAbg() {
        return configAbg;
    }

    public VelocityMain(ConfigAbg config) {
        this.configAbg = config;
    }

    public VelocityContext getContext() {
        return context;
    }

    public void prepare() throws Exception {
        ve.init();
        setarContext(context);

    }

    public void generate(String pathTemplate, String pathFile) throws Exception {
        LOG.info("Criando arquivo...");
        Template t = ve.getTemplate(pathTemplate);
        BufferedWriter crudWriter = getBufferedWriter(pathFile);
        t.merge(context, crudWriter);
        crudWriter.flush();
        crudWriter.close();
    }

    private BufferedWriter getBufferedWriter(String fileName) throws IOException {
        File w = new File(fileName);
        FileOutputStream os = new FileOutputStream(w);
        return new BufferedWriter(new OutputStreamWriter(os));
    }

    private void setarContext(VelocityContext context) {
        String nameClassRaw = configAbg.getClassName();
        String nameClass = nameClassRaw.trim().substring(0, 1).toUpperCase() + nameClassRaw.trim().substring(1);
        //Geral
        buildGeneralParams(context, nameClass);

    }


    private void buildGeneralParams(VelocityContext context, String nameClass) {
        context.put("nameClass", nameClass);
        context.put("dateCreated", new Date());
        context.put("listFields", configAbg.getFieldsAndTypes());
        context.put("package", configAbg.getPathPackage());
        context.put("containList", this.containList(configAbg.getFieldsAndTypes()));
        context.put("nameClassLower", nameClass.toLowerCase());
    }

    private boolean containList(Map<String, String> fieldsAndTypes) {
        for (String aux : fieldsAndTypes.values()) {
            if (aux.equals(ConfigEnum.TP_LIST.getValue())) {
                return true;
            }
        }
        return false;
    }
}
