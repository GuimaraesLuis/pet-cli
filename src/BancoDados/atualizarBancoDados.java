package BancoDados;
import Pessoa.Dono;
import Utils.ScreenBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class atualizarBancoDados {
    public void atualizarContaPet(Dono dono){
        ConectionBase.connect();

        PreparedStatement preparedStatement = null;
        String sql = "UPDATE tbl_pet" +
                " SET " +
                " fome = ?," +
                " energia = ?" +
                " sede = ?" +
                " inventario = ?";

        try{
            preparedStatement = ConectionBase.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, dono.getPet().getFome());
            preparedStatement.setInt(2, dono.getPet().getEnergia());
            preparedStatement.setInt(3, dono.getPet().getSede());
            preparedStatement.setString(3, new ScreenBuilder().serenizarHash(dono.getInventario()));
            preparedStatement.executeUpdate();
            System.out.println("PESSOA ATUALIZADA");
        }catch (SQLException | JsonProcessingException e){
            e.printStackTrace();
        }finally {
            try{
                preparedStatement.close();
                ConectionBase.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
