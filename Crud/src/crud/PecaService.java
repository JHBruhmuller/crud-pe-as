package crud;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PecaService implements PecaServiceInterface {

    @Override
    public void adicionarPeca(Peca peca) {
        String sql = "INSERT INTO pecas (codigo, nome, preco, tipo) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = ConexaoMySQL.getConexaoMySQL();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, peca.getCodigo());
            stmt.setString(2, peca.getNome());
            stmt.setDouble(3, peca.getPreco());
            stmt.setString(4, (peca instanceof PecaGenuina) ? "Genuina" : "SegundaLinha");

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar peça: " + e.getMessage());
        }
    }

    @Override
    public Peca buscarPeca(int codigo) {
        String sql = "SELECT * FROM pecas WHERE codigo = ?";
        
        try (Connection conn = ConexaoMySQL.getConexaoMySQL();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");
                String tipo = rs.getString("tipo");

                return tipo.equals("Genuina") 
                    ? new PecaGenuina(codigo, nome, preco / 1.05)
                    : new PecaSegundaLinha(codigo, nome, preco);
            } else {
                throw new RuntimeException("Peça não encontrada");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar peça: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void atualizarPeca(Peca pecaAtualizada) {
        String sql = "UPDATE pecas SET nome = ?, preco = ?, tipo = ? WHERE codigo = ?";
        
        try (Connection conn = ConexaoMySQL.getConexaoMySQL();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pecaAtualizada.getNome());
            stmt.setDouble(2, pecaAtualizada.getPreco());
            stmt.setString(3, (pecaAtualizada instanceof PecaGenuina) ? "Genuina" : "SegundaLinha");
            stmt.setInt(4, pecaAtualizada.getCodigo());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar peça: " + e.getMessage());
        }
    }

    @Override
    public void removerPeca(int codigo) {
        String sql = "DELETE FROM pecas WHERE codigo = ?";
        
        try (Connection conn = ConexaoMySQL.getConexaoMySQL();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, codigo);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao remover peça: " + e.getMessage());
        }
    }

    @Override
    public List<Peca> listarPecas() {
        List<Peca> pecas = new ArrayList<>();
        String sql = "SELECT * FROM pecas";
        
        try (Connection conn = ConexaoMySQL.getConexaoMySQL();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");
                String tipo = rs.getString("tipo");

                Peca peca = tipo.equals("Genuina") 
                    ? new PecaGenuina(codigo, nome, preco / 1.05)
                    : new PecaSegundaLinha(codigo, nome, preco);

                pecas.add(peca);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar peças: " + e.getMessage());
        }

        return pecas;
    }
}
