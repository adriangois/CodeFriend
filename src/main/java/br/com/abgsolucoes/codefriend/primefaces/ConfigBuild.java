package br.com.abgsolucoes.codefriend.primefaces;

import br.com.abgsolucoes.codefriend.primefaces.enums.ConfigEnum;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

public class ConfigBuild {

    private static Log LOG = LogFactory.getLog(ConfigBuild.class);

    public static ConfigAbg buildConfig(File f) throws IOException {
        ConfigAbg configAbg = new ConfigAbg();
        BufferedReader r = new BufferedReader(new FileReader(f));
        configAbg.setTypesGenerateName(new ArrayList<String>());
        configAbg.setFieldsAndTypes(new HashMap<>());
        String line = r.readLine();



        while (line != null) {
            LOG.info("Configurando " + line);
            //System.out.println("Configura o " + line);
            if(!verifyComment(line)) {

                if (line.endsWith(":")) {
                    //Lista de Tipos
                    if (line.trim().equals(ConfigEnum.TAG_TPS.getValue())) {
                        line = getType(r, configAbg);
                    }
                    //Nome do caso de Uso
                    if (line.trim().equals(ConfigEnum.TAG_OBJ.getValue())) {
                        line = getNameObj(r, configAbg);
                    }

                    if (line.trim().equals(ConfigEnum.TAG_FIELDS.getValue())) {
                        line = getFieldsObj(r, configAbg);
                    }

                    if (line.trim().equals(ConfigEnum.TAG_PKG.getValue())) {
                        line = getFieldPackage(r, configAbg);
                    }

                    line = r.readLine();


                } else {
                    line = r.readLine();
                }
            }else{
                line = r.readLine();
            }
        }


        return configAbg;
    }

    private static boolean verifyComment(String line) {
        if(line.trim().startsWith("#")){
            return true;
        }
        return false;
    }

    private static String getFieldPackage(BufferedReader r, ConfigAbg config) throws IOException {
        String line;
        line = r.readLine();
        if (line != null) {
            while ((line != null) && (!line.endsWith(":"))) {
                String[] auxListFileds = line.trim().split("=");
                config.setPathPackage(auxListFileds[1]);
                line = r.readLine();
            }

        }
        return line;
    }


    private static String getFieldsObj(BufferedReader r, ConfigAbg config) throws IOException {
        String line;
        line = r.readLine();
        if (line != null) {
            while ((line != null) && (!line.endsWith(":"))) {
                String[] auxListFileds = line.trim().split("-");
                config.getFieldsAndTypes().put(auxListFileds[0], auxListFileds[1]);
                line = r.readLine();
            }
        }
        return line;
    }

    private static String getType(BufferedReader r, ConfigAbg config) throws IOException {
        String line;
        List<String> listaTypes;
        line = r.readLine();
        if(!verifyComment(line)){
            listaTypes = Arrays.asList(line.trim().split(","));
            List<String> listAux = new ArrayList<>();
            for(String aux : listaTypes)
                listAux.add(aux.trim());
            config.getTypesGenerateName().addAll(listAux);
        }else{
            getType(r,config);
        }
        return line;
    }


//    public static void main(String[] s){
//        String line = "name=camel_back";
//        String[] aux = line.trim().split("=");
//
//        if(aux[1].trim().contains("_")){
//            String[] arrayName =  aux[1].trim().split("_");
//            char camel = arrayName[1].charAt(0);
//            String c = new String(String.valueOf(camel));
//            System.out.println((arrayName[0] + c.toUpperCase() + arrayName[1].trim().substring(1,arrayName[1].length())));
//        }else{
//            System.out.println((aux[1]));
//        }
//    }


    private static String getNameObj(BufferedReader r, ConfigAbg config) throws IOException {
        String line;
        List<String> listaTypes;
        line = r.readLine();
        String[] aux = line.trim().split("=");
        if(aux[1].trim().contains("_")){
            String[] arrayName =  aux[1].trim().split("_");
            char camel = arrayName[1].charAt(0);
            String c = new String(String.valueOf(camel));
            config.setClassName(arrayName[0] + c.toUpperCase() + arrayName[1].trim().substring(1,arrayName[1].length()));
        }else{
            config.setClassName(aux[1]);
        }
        LOG.info("Class Name = " + config.getClassName());
        return line;
    }


    public static void main(String[] args) {
        String s = "relmo";
        System.out.println(s.startsWith("rel"));
    }
}
