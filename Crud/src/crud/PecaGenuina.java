package crud;

public class PecaGenuina extends Peca {
    public PecaGenuina(int codigo, String nome, double preco) {
        super(codigo, nome, preco * 1.05);
    }

    @Override
    public double calcularPreco() {
        return getPreco();
    }
}