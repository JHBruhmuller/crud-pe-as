package crud;

import java.util.List;

public interface PecaServiceInterface {
    void adicionarPeca(Peca peca);
    
    Peca buscarPeca(int codigo);
    
    void atualizarPeca(Peca peca);
    
    void removerPeca(int codigo);
    
    List<Peca> listarPecas();
}