package br.com.abgsolucoes.sigaweb.tests;

import br.com.abgsolucoes.core.entity.Fornecedor;
import br.com.abgsolucoes.core.vo.FornecedorVO;
import br.com.abgsolucoes.sigaweb.tests.util.AbstractUtilTest;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by adrian on 12/11/15.
 */
public class VelocityTest extends AbstractUtilTest{

    private String pathTemplateCrud = "/src/test/java/br/com/abgsolucoes/sigaweb/tests/templates/crud-list.vm";
    private String pathTemplateNovo = "/src/test/java/br/com/abgsolucoes/sigaweb/tests/templates/novo-xhtml.vm";
    private String pathTemplateJava = "/src/test/java/br/com/abgsolucoes/sigaweb/tests/templates/crud-mb-java.vm";
    private String pathTemplateJavaDAOTest = "/src/test/java/br/com/abgsolucoes/sigaweb/tests/templates/class-testDAO-template.vm";
    private String pathTemplateJavaServiceTest = "/src/test/java/br/com/abgsolucoes/sigaweb/tests/templates/crud-testDAO-template.vm";
    private String pathTemplateFacesConfigNav = "/src/test/java/br/com/abgsolucoes/sigaweb/tests/templates/faces-config-navegation.vm";
    private static String pathTemplateProperties = TemplatesUtilEnum.BASE_PATH_TEMPLATES.getValor() + "template-properties.vm";
    private static String pathTemplateServiceTest = TemplatesUtilEnum.BASE_PATH_TEMPLATES.getValor() + "class-testService-template.vm";



    private String nomeCrud;
    private String nomeCrudCamel;
    private Configuracao config = new Configuracao();

    @Test
    public void shoulWriteHello() throws IOException {
        VelocityEngine ve = new VelocityEngine();
        ve.init();
        VelocityContext context = new VelocityContext();
        setarContext(context);

        //XHTML para List

        BufferedWriter crudWriter;
        if(config.getValor(TemplatesUtilEnum.LIST)){
            Template t = ve.getTemplate(pathTemplateCrud);
            crudWriter = getBufferedWriter("./src/test/java/br/com/abgsolucoes/sigaweb/tests/saida/crud-"+nomeCrud+"s.xhtml");
            t.merge(context, crudWriter);
            crudWriter.flush();
            crudWriter.close();
        }

        //XHTMl para novo
        if(config.getValor(TemplatesUtilEnum.NOVO)) {
            crudWriter = getBufferedWriter("./src/test/java/br/com/abgsolucoes/sigaweb/tests/saida/novo-" + nomeCrud + ".xhtml");
            ve.getTemplate(pathTemplateNovo).merge(context, crudWriter);
            crudWriter.flush();
            crudWriter.close();
        }


        ///Gerando ManagedBean
        if(config.getValor(TemplatesUtilEnum.BEAN)) {
            crudWriter = getBufferedWriter("./src/test/java/br/com/abgsolucoes/sigaweb/tests/saida/" + nomeCrudCamel + "Bean.java");
            ve.getTemplate(pathTemplateJava).merge(context, crudWriter);
            crudWriter.flush();
            crudWriter.close();
        }

        ///Gerando DAO test
        if(config.getValor(TemplatesUtilEnum.TEST_DAO)) {
            crudWriter = getBufferedWriter("./src/test/java/br/com/abgsolucoes/sigaweb/tests/saida/" + nomeCrudCamel + "DAOTest.java");
            ve.getTemplate(pathTemplateJavaDAOTest).merge(context, crudWriter);
            crudWriter.flush();
            crudWriter.close();
        }
        //FacesConfig nav
        if(config.getValor(TemplatesUtilEnum.FACES_NAV)) {
            crudWriter = getBufferedWriter("./src/test/java/br/com/abgsolucoes/sigaweb/tests/saida/faces-navigation.txt");
            ve.getTemplate(pathTemplateFacesConfigNav).merge(context, crudWriter);
            crudWriter.flush();
            crudWriter.close();
        }

        if(config.getValor(TemplatesUtilEnum.MESSAGES_PROPERTIES)){
            crudWriter = getBufferedWriter(TemplatesUtilEnum.BASE_PATH_SAIDA.getValor() + "messages_pt_BR.properties");
            ve.getTemplate(pathTemplateProperties).merge(context, crudWriter);
            crudWriter.flush();
            crudWriter.close();

        }

        if(config.getValor(TemplatesUtilEnum.TEST_SERVICE)){
            crudWriter = getBufferedWriter(TemplatesUtilEnum.BASE_PATH_SAIDA.getValor() + nomeCrudCamel+"ServiceTest.java");
            ve.getTemplate(pathTemplateServiceTest).merge(context, crudWriter);
            crudWriter.flush();
            crudWriter.close();

        }

    }

    private BufferedWriter getBufferedWriter(String fileName) throws FileNotFoundException {
        File w = new File(fileName);
        FileOutputStream os = new FileOutputStream(w);
        return new BufferedWriter(new OutputStreamWriter(os));
    }

    private void setarContext(VelocityContext context){
        Object vo = config.getVO();
        String nomeVO = vo.getClass().getSimpleName().toLowerCase();
        String[] splitManagedBean = vo.getClass().getSimpleName().split("V");
        this.nomeCrud = splitManagedBean[0].toLowerCase();
        String manageBean = nomeCrud + "MB";
        this.nomeCrudCamel = nomeCrud.substring(0,1).toUpperCase() + nomeCrud.substring(1,nomeCrud.length());



        context.put("nomeVOCamel", vo.getClass().getSimpleName());
        context.put("nomeVO", nomeVO);
        context.put("nomeCrud", nomeCrud);//de PedidoVO = pedido
        context.put("mb",manageBean);
        context.put("nomeParamBusca",TemplatesUtilEnum.FILTRO_BUSCA.getValor());
        context.put("tableUpdate", nomeCrud + "DT");
        context.put("nomeMetodoBusca", TemplatesUtilEnum.METODO_BUSCA.getValor());
        context.put("nomeLista", TemplatesUtilEnum.PREFIX_LISTA.getValor() + nomeCrudCamel + "s");
        context.put("nomeListaCamel", TemplatesUtilEnum.PREFIX_LISTA_CAMEL.getValor() + nomeCrudCamel + "s");
        context.put("listaCampos", parametrosVO());
        context.put("nomeForward", "novo-" + nomeCrud);
        context.put("voSelecionado", nomeCrud + TemplatesUtilEnum.SUFFIX_SELECIONADO.getValor());
        context.put("nomeMetodoDelete", TemplatesUtilEnum.ACTION_DELETAR.getValor());
        context.put("nomeCrudSalvar", nomeCrud + TemplatesUtilEnum.SUFIX_CRUD_SALVAR.getValor());
        context.put("nomeCrudSalvarCamel", nomeCrudCamel + TemplatesUtilEnum.SUFIX_CRUD_SALVAR.getValor());
        context.put("nomeActionSalvar", TemplatesUtilEnum.ACTION_SALVAR.getValor());
        context.put("nomeCrudCamel", nomeCrudCamel);

        //Para Java
        context.put("package", TemplatesUtilEnum.PACKAGE.getValor());
        context.put("packageService", TemplatesUtilEnum.PACKAGESERVICE.getValor());

        context.put("packageVO", vo.getClass().getPackage().getName());
        context.put("nomeClasse",  nomeCrudCamel + "Bean");
        context.put("voSetadoId",  nomeCrud + "ID");
        context.put("dataCriacao", new Date());

        //Para Testes Java

        context.put("nomeDaoCamel",nomeCrudCamel + "DAO");
        context.put("nomeDao",nomeCrud+"DAO");
        context.put("listaSettersEntity", listaSettersEntity());

        //context.put("nomeEntity", );
        context.put("nomeEntityCamel", config.getEntity().getClass().getSimpleName());
        context.put("nomeServiceCamel" , context.get("nomeEntityCamel") + "Service");
        context.put("nomeService" ,  nomeCrud + "Service");

        //FacesConfig
        Navegation nav = new Navegation("/pages/seguranca/"+nomeCrud+"s/novo-" +nomeCrud+ ".xhtml"
        ,"voltar-" + nomeCrud+"s","/pages/seguranca/"+nomeCrud+"s/crud-" +nomeCrud + "s.xhtml");

        context.put("fromViewId",nav.getFromViewId());
        context.put("fromOutCome",nav.getFromOutCome());
        context.put("toViewId", nav.getToViewId());


    }


    public   List<Field> parametrosVO(){
        Field[] declaredFields = config.getVO().getClass().getDeclaredFields();
        return Arrays.asList(declaredFields);
    }

    public List<Method> listaSettersEntity(){
        Method[] methods = config.getEntity().getClass().getMethods();
        List<Method> metodos = Arrays.asList(methods);
        return metodos.stream().filter(m -> m.getName().contains("set")).collect(Collectors.toList());
    }


    class Configuracao{
        private Map<TemplatesUtilEnum, Boolean> config = new HashMap<TemplatesUtilEnum, Boolean>();

        private Object objetoVO;
        private Object entity;


        public Configuracao(){
            config.put(TemplatesUtilEnum.LIST, true );
            config.put(TemplatesUtilEnum.NOVO, true );
            config.put(TemplatesUtilEnum.BEAN, true );
            config.put(TemplatesUtilEnum.TEST_DAO, true );
            config.put(TemplatesUtilEnum.TEST_SERVICE, true );
            config.put(TemplatesUtilEnum.FACES_NAV,true );
            config.put(TemplatesUtilEnum.MESSAGES_PROPERTIES, true);

            /**
             * CONFIGURACAO
             */
            this.objetoVO = new FornecedorVO();
            this.entity = new Fornecedor();



        }

        public Boolean getValor(TemplatesUtilEnum t){
            return config.get(t);
        }

        public Object getVO(){
            return objetoVO;
        }

        public Object getEntity(){
            return this.entity;
        }

    }


    class Navegation{

        private String fromViewId;
        private String fromOutCome;
        private String toViewId;

        public Navegation(String from, String out, String viewId){
            this.fromViewId = from;
            this.fromOutCome = out;
            this.toViewId =  viewId;
        }

        public String getFromViewId() {
            return fromViewId;
        }

        public void setFromViewId(String fromViewId) {
            this.fromViewId = fromViewId;
        }

        public String getFromOutCome() {
            return fromOutCome;
        }

        public void setFromOutCome(String fromOutCome) {
            this.fromOutCome = fromOutCome;
        }

        public String getToViewId() {
            return toViewId;
        }

        public void setToViewId(String toViewId) {
            this.toViewId = toViewId;
        }
    }
}
