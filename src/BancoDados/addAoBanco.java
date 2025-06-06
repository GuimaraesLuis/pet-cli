package BancoDados;
import Fauna.Animais;
import Pessoa.Dono;
import Utils.ScreenBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class addAoBanco {
    public Dono adicionarPessoa(Dono pessoa) throws SQLException {
        CriarTabelasSqlite criarTabelasSqlite = new CriarTabelasSqlite();
        criarTabelasSqlite.addTablePessoa();
        String sqlInsert = "INSERT INTO tbl_petcli (" +
                "nome," +
                "senha," +
                "pet) VALUES(?,?,?)" +
                ";";

        PreparedStatement preparedStatement = ConectionBase.getConnection().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);

        try {
            preparedStatement.setString(1, pessoa.getName());
            preparedStatement.setString(2, pessoa.getSenha());
            preparedStatement.setString(3, pessoa.getPet().getNome());

            int result = preparedStatement.executeUpdate();
            if(result == 1){
                System.out.println("pessoa inserida");
            }else {
                System.out.println("pessoa n inserida");
            }
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()){
                pessoa.setId(resultSet.getInt(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(preparedStatement != null){
                    preparedStatement.close();

                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return pessoa;
    }
    public Animais adicionarPet(Animais pet) throws SQLException {
        CriarTabelasSqlite criarTabelasSqlite = new CriarTabelasSqlite();
        criarTabelasSqlite.addTablePet();

        String sql = "INSERT INTO tbl_pet (" +
                "nome," +
                "donoId," +
                "fome," +
                "energia," +
                "sede," +
                "tipo," +
                "inventario) VALUES (?,?,?,?,?,?,?)" +
                ";";

        PreparedStatement preparedStatement = ConectionBase.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        try {
            Dono dono = new PesquisasBanco().pesquisarPessoa(pet.getDono());
            HashMap<String, Integer> hashMap = dono.getInventario();
            preparedStatement.setString(1, pet.getNome());
            preparedStatement.setInt(2, dono.getId());
            preparedStatement.setInt(3, pet.getFome());
            preparedStatement.setInt(4, pet.getEnergia());
            preparedStatement.setInt(5, pet.getSede());
            preparedStatement.setString(6, String.valueOf(pet.getTipo()));
            preparedStatement.setString(7, new ScreenBuilder().serenizarHash(hashMap));
            int result = preparedStatement.executeUpdate();
            if(result == 1){
                System.out.println("Pet inserido");
            }else {
                System.out.println("Pet n inserido");
            }
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()){
                pet.setId(resultSet.getInt(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } finally {
            try{
                if(preparedStatement != null){
                    preparedStatement.close();

                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return pet;
    }
}
