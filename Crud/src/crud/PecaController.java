package crud;

import java.util.List;
import java.util.Scanner;

public class PecaController {
    private PecaService pecaService = new PecaService();
    private Scanner scanner = new Scanner(System.in);

    public void iniciar() {
        int opcao;
        do {
            System.out.println("\nEscolha uma opera��o: ");
            System.out.println("1 - Adicionar Pe�a");
            System.out.println("2 - Buscar Pe�a");
            System.out.println("3 - Atualizar Pe�a");
            System.out.println("4 - Remover Pe�a");
            System.out.println("5 - Listar Pe�as");
            System.out.println("0 - Sair");
            System.out.print("Op��o: \n");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> adicionarPeca();
                case 2 -> buscarPeca();
                case 3 -> atualizarPeca();
                case 4 -> removerPeca();
                case 5 -> listarPecas();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Op��o inv�lida!");
            }
        } while (opcao != 0);
    }

    private void adicionarPeca() {
        System.out.print("Tipo de pe�a: \n 1 - Genu�na \n 2 - Segunda Linha \n Qual?");
        int tipo = scanner.nextInt();
        System.out.print("C�digo: ");
        int codigo = scanner.nextInt();
        System.out.print("Nome: ");
        scanner.nextLine();
        String nome = scanner.nextLine();
        System.out.print("Pre�o: ");
        double preco = scanner.nextDouble();

        Peca peca;
        if (tipo == 1) {
            peca = new PecaGenuina(codigo, nome, preco);
        } else {
            peca = new PecaSegundaLinha(codigo, nome, preco);
        }

        try {
            pecaService.adicionarPeca(peca);
            System.out.println("Pe�a adicionada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao adicionar pe�a: " + e.getMessage());
        }
    }

    private void buscarPeca() {
        System.out.print("C�digo da pe�a: ");
        int codigo = scanner.nextInt();
        try {
            Peca peca = pecaService.buscarPeca(codigo);
            System.out.println("Pe�a encontrada: " + peca.getNome() + ", Pre�o: " + peca.calcularPreco());
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void atualizarPeca() {
        System.out.print("C�digo da pe�a para atualizar: ");
        int codigo = scanner.nextInt();
        System.out.print("Novo nome: ");
        scanner.nextLine(); 
        String nome = scanner.nextLine();
        System.out.print("Novo pre�o: ");
        double preco = scanner.nextDouble();
        
        Peca peca;
        try {
            peca = pecaService.buscarPeca(codigo);
            if (peca instanceof PecaGenuina) {
                peca = new PecaGenuina(codigo, nome, preco);
            } else {
                peca = new PecaSegundaLinha(codigo, nome, preco);
            }
            pecaService.atualizarPeca(peca);
            System.out.println("Pe�a atualizada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar pe�a: " + e.getMessage());
        }
    }

    private void removerPeca() {
        System.out.print("C�digo da pe�a para remover: ");
        int codigo = scanner.nextInt();
        try {
            pecaService.removerPeca(codigo);
            System.out.println("Pe�a removida com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao remover pe�a: " + e.getMessage());
        }
    }

    private void listarPecas() {
        List<Peca> pecas = pecaService.listarPecas();
        if (pecas.isEmpty()) {
            System.out.println("Nenhuma pe�a encontrada.");
        } else {
            System.out.println("Lista de Pe�as:");
            for (Peca peca : pecas) {
                System.out.println("C�digo: " + peca.getCodigo() + ", Nome: " + peca.getNome() + ", Pre�o: " + peca.calcularPreco());
            }
        }
    }
}
