package br.com.abgsolucoes.codefriend.primefaces;

import br.com.abgsolucoes.codefriend.primefaces.enums.ConfigEnum;
import java.io.*;

public class Main {
    private static String dirTemplates = "/src/main/resources/templates/";
    private static String pathTemplateVO = dirTemplates + "generate-vo.vm";
    private static String pathTemplateEntityJPA = dirTemplates + "generate-entity-jpa.vm";
    private static String pathTemplateEndPointAterra = dirTemplates + "aterralimpa/generate-endpoint-aterra.vm";
    private static String pathTemplateBeanAterra = dirTemplates + "aterralimpa/generate-bean-aterralimpa.vm";
    private static String pathTemplateDaoAterraAterra = dirTemplates + "aterralimpa/generate-dao-aterralimpa.vm";
    private static String pathTemplateRepositoryAterra = dirTemplates + "aterralimpa/generate-repository-aterra.vm";
    private static String pathTemplateXhtmlListAterra = dirTemplates + "aterralimpa/generate-xhtml-list-aterra.vm";
    private static String pathTemplatexpto = dirTemplates + "aterralimpa/xpto.vm";
    private static String dirOut = System.getProperty("user.dir") + "/saida";

    public static void  main(String arg[]) throws Exception {

        File f = new File(System.getProperty("user.dir") + "/config/config.abg_old");
        VelocityMain m = new VelocityMain(ConfigBuild.buildConfig(f));


        m.prepare();

        //Plugins
        if(m.getConfigAbg().containListGenerate(ConfigEnum.TAG_VO)){
            m.generate(pathTemplateVO,dirOut + "/"+ m.getContext().get("nameClass") + "DTO.java");
        }
        if(m.getConfigAbg().containListGenerate(ConfigEnum.TAG_ENTITY_JPA)){
            m.generate(pathTemplateEntityJPA, dirOut + "/"+ m.getContext().get("nameClass") + ".java");
        }
        if(m.getConfigAbg().containListGenerate(ConfigEnum.TAG_ENDPOINT_ATERRA)){
            m.generate(pathTemplateEndPointAterra, dirOut + "/"+ m.getContext().get("nameClass") + "Endpoint.java");
        }
        if(m.getConfigAbg().containListGenerate(ConfigEnum.TAG_REPOSITORY_ATERRA)){
            m.generate(pathTemplateRepositoryAterra, dirOut + "/"+ m.getContext().get("nameClass") + "Repository.java");
        }
        if(m.getConfigAbg().containListGenerate(ConfigEnum.TAG_BEAN_ATERRA)){
            m.generate(pathTemplateBeanAterra, dirOut + "/"+ m.getContext().get("nameClass") + "Bean.java");
        }
        if(m.getConfigAbg().containListGenerate(ConfigEnum.TAG_DAO_ATERRA)){
            m.generate(pathTemplateDaoAterraAterra, dirOut + "/"+ m.getContext().get("nameClass") + "DAO.java");
        }
        if(m.getConfigAbg().containListGenerate(ConfigEnum.TAG_XHTML_ATERRA)){
            m.generate(pathTemplateXhtmlListAterra, dirOut + "/list.xhtml");
        }
        if(m.getConfigAbg().containListGenerate(ConfigEnum.TAG_XPTO)){
            m.generate(pathTemplatexpto, dirOut + "/XPTO.jrxml");
        }


    }
}
