package br.com.abgsolucoes.codefriend.primefaces;

import br.com.abgsolucoes.codefriend.primefaces.enums.ConfigEnum;

import java.util.List;
import java.util.Map;

/**
 * Classe responsavel por setar objetos do arquivo config.abg
 */
public class ConfigAbg {

    private String className;
    private List<String> typesGenerateName;
    private Map<String, String> fieldsAndTypes;
    private String pathPackage;

    public String getPathPackage() {
        return pathPackage;
    }

    public void setPathPackage(String pathPackage) {
        this.pathPackage = pathPackage;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<String> getTypesGenerateName() {
        return typesGenerateName;
    }

    public void setTypesGenerateName(List<String> typesGenerateName) {
        this.typesGenerateName = typesGenerateName;
    }

    public Map<String, String> getFieldsAndTypes() {
        return fieldsAndTypes;
    }

    public void setFieldsAndTypes(Map<String, String> fieldsAndTypes) {
        this.fieldsAndTypes = fieldsAndTypes;
    }

    public  boolean containListGenerate(ConfigEnum o){
        for(String s : this.getTypesGenerateName()){
            if(o.getValue().equals(s)){
                return true;
            }
        }
        return false;
    }


}
