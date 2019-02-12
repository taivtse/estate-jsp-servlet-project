import com.laptrinhjavaweb.core.util.IModelAnnotationUtil;
import com.laptrinhjavaweb.model.*;

public class TestAnnotation {
    public static void main(String[] args) {
        Class<?> modelClass = BuildingModel.class;

        String sql1 = IModelAnnotationUtil.of(modelClass).buildSelectStatement();
        System.out.println("SELECT ALL: \n" + sql1);


        String sql2 = IModelAnnotationUtil.of(modelClass).buildSelectByIdStatement();
        System.out.println("\n\nSELECT BY ID: \n" + sql2);

        String sql3 = IModelAnnotationUtil.of(modelClass).buildInsertStatement();
        System.out.println("\n\nINSERT: \n" + sql3);

        String sql4 = IModelAnnotationUtil.of(modelClass).buildUpdateByIdStatement();
        System.out.println("\n\nUPDATE: \n" + sql4);

        String sql5 = IModelAnnotationUtil.of(modelClass).buildDeleteByIdStatement();
        System.out.println("\n\nDELETE: \n" + sql5);
    }
}
