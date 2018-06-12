package br.com.abgsolucoes.codefriend.primefaces.enums;

public enum ConfigEnum {

    TAG_TPS("tps:"),
    TAG_VO("vo"),
    TAG_NAME("name"),
    TAG_FIELDS("fields:"),
    TAG_OBJ("obj:"),
    TAG_STR("str"),
    TYPES_LIST("types"),
    FIELDS_TYPES("field_types"),
    TAG_INT("int"), TAG_PKG("pkg:"), TP_LIST("lt"), TAG_ENTITY_JPA("jpa"),
    TAG_ENDPOINT_ATERRA("endpoint-aterra"),
    TAG_REPOSITORY_ATERRA("repository-aterra"), TAG_BEAN_ATERRA("bean-aterra"), TAG_DAO_ATERRA("dao-aterra"), TAG_XPTO("xtpogenerate"), TAG_XHTML_ATERRA("xhtml-aterra");

    private String value;

    ConfigEnum(String f){
        this.value = f;
    }

    public String getValue(){
        return this.value;
    }
}
