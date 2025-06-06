package BancoDados;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CriarTabelasSqlite {

    public void addTablePessoa(){
        String sql = "CREATE TABLE IF NOT EXISTS tbl_petcli" +
                "(" +
                "id integer PRIMARY KEY AUTO_INCREMENT," +
                "nome text NOT NULL," +
                "senha text NOT NULL," +
                "pet text NOT NULL" +
                ")";
        try{
            Connection connection = ConectionBase.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void addTablePet(){
        String sql = "CREATE TABLE IF NOT EXISTS tbl_pet" +
                "(" +
                "id integer PRIMARY KEY AUTO_INCREMENT," +
                "nome text NOT NULL," +
                "donoId integer NOT NULL," +
                "fome integer NOT NULL," +
                "energia integer NOT NULL," +
                "sede integer NOT NULL," +
                "tipo text NOT NULL," +
                "inventario text NOT NULL" +
                ")";

        try{
            Connection connection = ConectionBase.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
