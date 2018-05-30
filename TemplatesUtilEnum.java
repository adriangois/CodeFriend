package br.com.abgsolucoes.sigaweb.tests;

/**
 * Created by adrian on 12/11/15.
 */
public enum TemplatesUtilEnum {

    METODO_BUSCA("buscar"), FILTRO_BUSCA("filtroBusca"), ACTION_SALVAR("salvar"), ACTION_DETALHAR("detalhar")
    ,ACTION_DELETAR("deletar"), ACTION_EXCLUIR("excluir"), SUFFIX_DATA_TABLE("DT"), SUFIXX_FORM("form"), PREFIX_LISTA("lista")
    ,SUFFIX_SELECIONADO("Selecionado"), SUFIX_CRUD_SALVAR("Salvar"), PACKAGE("br.com.abgsolucoes.sigaweb.seguranca.bean"),
    PACKAGESERVICE("br.com.abgsolucoes.seguranca.service."), LIST("lista"), NOVO("novo"), BEAN("bean"), TEST_DAO("dao"),
    TEST_SERVICE("service"), FACES_NAV("facesConfig"), PREFIX_LISTA_CAMEL("Lista"), BASE_PATH_TEMPLATES("/src/test/java/br/com/abgsolucoes/sigaweb/tests/templates/"), BASE_PATH_SAIDA("./src/test/java/br/com/abgsolucoes/sigaweb/tests/saida/"), MESSAGES_PROPERTIES("messagesProperties");

    private String valor;

    private TemplatesUtilEnum(String valor){
        this.valor = valor;
    }

    public String getValor(){
        return this.valor;
    }
}
