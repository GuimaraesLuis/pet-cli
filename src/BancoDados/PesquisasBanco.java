package BancoDados;

import Fauna.Animais;
import Fauna.Cachorro;
import Fauna.Gatos;
import Fauna.TipoAnimal;
import Pessoa.Dono;
import Utils.ScreenBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PesquisasBanco {
    public boolean vericarNome(String nome) {
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        String nomeEncontrado = null;


        String sql = "SELECT * FROM tbl_petcli WHERE nome LIKE ?";

        try {
            statement = ConectionBase.getConnection().prepareStatement(sql);
            statement.setString(1, "%" + nome + "%");
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                nomeEncontrado = resultSet.getString("nome");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(resultSet != null) {
                    resultSet.close();
                    statement.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        if (nomeEncontrado != null) {
            return false;
        } else {
            return true;
        }
    }

    public Animais buscaPet (int idDono) {
        Animais animais = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;

        String sql = "SELECT * FROM tbl_pet WHERE donoId = ?";

        try {
            statement = ConectionBase.getConnection().prepareStatement(sql);
            statement.setInt(1, idDono);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String tipo = resultSet.getString("tipo");
                TipoAnimal tipoAnimal = TipoAnimal.valueOf(tipo);

                switch (tipoAnimal) {
                    case Cachorro:
                        animais = new Cachorro();
                        break;
                    case Gato:
                        animais = new Gatos();
                        break;
                    default:
                        return null;
                }

                animais.setId(resultSet.getInt("id"));
                animais.setEnergia(resultSet.getInt("energia"));
                animais.setFome(resultSet.getInt("fome"));
                animais.setSede(resultSet.getInt("sede"));
                animais.setDono(String.valueOf(resultSet.getInt("donoId")));
                animais.setNome(resultSet.getString("nome"));
                animais.setTipo(TipoAnimal.valueOf(resultSet.getString("tipo")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return animais;
    }
    public Dono pesquisarPessoa(String nome) throws SQLException {
        Dono dono = new Dono();
        boolean pessoaRetornada = false;
        ResultSet resultSet = null;
        PreparedStatement statement = null;

        String sql = "SELECT * FROM tbl_petcli WHERE nome = ?";

        try {
            statement = ConectionBase.getConnection().prepareStatement(sql);
            statement.setString(1, nome);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                pessoaRetornada = true;
                dono.setId(resultSet.getInt("id"));
                dono.setName(resultSet.getString("nome"));
                dono.setSenha(resultSet.getString("senha"));
                dono.setPet(buscaPet(resultSet.getInt("id")));
                dono.setInventario(new ScreenBuilder().desserenizarHash(resultSet.getString("inventario")));
                dono.setCoins(resultSet.getFloat("coins"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        if (pessoaRetornada != false) {
            return dono;
        } else {
            return null;
        }
    }
public Dono pesquisarLogin(String nome, String senha) throws SQLException {
            Dono dono = new Dono();
            boolean pessoaRetornada = false;
            ResultSet resultSet = null;
            PreparedStatement statement = null;

            String sql = "SELECT * FROM tbl_petcli WHERE nome = ? AND senha = ?";

            try {
                statement = ConectionBase.getConnection().prepareStatement(sql);
                statement.setString(1, nome);
                statement.setString(2, senha);
                resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    pessoaRetornada = true;
                    dono.setId(resultSet.getInt("id"));
                    dono.setName(resultSet.getString("nome"));
                    dono.setSenha(resultSet.getString("senha"));
                    dono.setPet(buscaPet(resultSet.getInt("id")));
                    dono.setInventario(new ScreenBuilder().desserenizarHash(resultSet.getString("inventario")));
                    dono.setCoins(resultSet.getFloat("coins"));
                }else{
                    System.out.println("pessoa nn encontrada");
                }
            } catch (SQLException | JsonProcessingException e) {
                e.printStackTrace();
            } finally {
                try {
                    resultSet.close();
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            if (pessoaRetornada != false) {
                return dono;
            } else {
                return null;
            }
        }
    }

