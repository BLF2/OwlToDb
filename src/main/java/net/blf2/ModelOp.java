package net.blf2;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.ModelMaker;

import java.io.*;

/**
 * Created by blf2 on 17-6-28.
 */
public class ModelOp {

    public static void insertData(){
        InputStreamReader in = null;
        try {
            in = new InputStreamReader(new FileInputStream(new File(Consts.owlFilePath)),"UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("未找到ＯＷＬ文件");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("未知字符集");
        }
        ModelMaker modelMaker = ModelFactory.createModelRDBMaker(DbDriver.idbConnection);
        Model defModel = null;
        if(DbDriver.idbConnection.containsModel("militaryDB")){
            defModel = modelMaker.openModel("militaryDB",true);
            System.out.println("打开已存在的模型");
        }else{
            defModel = modelMaker.createModel("militaryDB");
            System.out.println("创建了一个新模型");
        }
        OntModelSpec spec = new OntModelSpec(OntModelSpec.OWL_MEM);
        OntModel dbModel = ModelFactory.createOntologyModel(spec,defModel);
        dbModel.read(in,null);
        dbModel.commit();
        System.out.println("已将数据存入数据库，等待关闭资源...");
        DbDriver.closeConnection();
        try {
            in.close();
            dbModel.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("关闭文件失败");
        }
        System.out.println("文件关闭完成");
        System.out.println("资源全部关闭");
    }

}
