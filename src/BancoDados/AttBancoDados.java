package BancoDados;
import Pessoa.Dono;
import Utils.ScreenBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AttBancoDados {
    public void atualizarContaPet(Dono dono){
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE tbl_pet" +
                " SET " +
                " fome = ?, " +
                " energia = ?, " +
                " sede = ? " +
                " WHERE id = ? ";

        try{
            preparedStatement = ConectionBase.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, dono.getPet().getFome());
            preparedStatement.setInt(2, dono.getPet().getEnergia());
            preparedStatement.setInt(3, dono.getPet().getSede());
            preparedStatement.setInt(4, dono.getPet().getId());
            preparedStatement.executeUpdate();
            System.out.println("PET ATUALIZADA");
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                preparedStatement.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    public void atualizarContaPessoa(Dono dono){
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE tbl_petcli" +
                " SET " +
                " inventario = ?, " +
                " coins = ? " +
                "WHERE id = ?";

        try {
            preparedStatement = ConectionBase.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, new ScreenBuilder().serenizarHash(dono.getInventario()));
            preparedStatement.setFloat(2, dono.getCoins());
            preparedStatement.setInt(3, dono.getId());
            preparedStatement.executeUpdate();
            System.out.println("Pessoa Atualizada");
        }catch (SQLException e){
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
