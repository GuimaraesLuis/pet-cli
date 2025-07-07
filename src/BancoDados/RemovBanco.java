package BancoDados;

import Fauna.Animais;
import Pessoa.Dono;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemovBanco {

    public void RemovPessoa(Dono dono) {
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM tbl_petcli " +
                " WHERE id = ?;";

        try{
            preparedStatement = ConectionBase.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, dono.getId());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                preparedStatement.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public void RmemovAnimal(Animais animal){
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM tbl_pet " +
                " WHERE id = ?;";

        try{
            preparedStatement = ConectionBase.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, animal.getId());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                preparedStatement.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
