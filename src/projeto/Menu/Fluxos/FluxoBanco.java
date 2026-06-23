package projeto.Menu.Fluxos;

import projeto.entidades.ContaBancaria;
import projeto.entidades.entidadeDeDominio.Banco;
import projeto.exception.NegocioException;
import projeto.repositorios.BancoRepository;

import java.util.Scanner;

public class FluxoBanco {

    private Scanner scanner;
    private BancoRepository bancoRepository;

    public FluxoBanco(Scanner scanner, BancoRepository bancoRepository) {
        this.scanner = scanner;
        this.bancoRepository = bancoRepository;
    }

    public void validarSenhaBanco(ContaBancaria c){
        while (true) {

            System.out.println("Informe sua senha ");
            System.out.print("-> ");
            String senhaBanco = scanner.nextLine();


            if (!c.validarSenha(c.getNumeroConta(), senhaBanco)) {
                System.out.println("Senha ou numero da conta inválido");
                continue;
            }

            System.out.println("Conta localizada!");
            System.out.println("----------------------------");

            break;
        }
    }

    public ContaBancaria solicitarContaBancaria(Banco b){

        System.out.println("Informe o numero da conta");
        System.out.print("-> ");
        String numeroConta = scanner.nextLine();

        if (numeroConta == null || numeroConta.isBlank())
            throw new NegocioException("Numero de conta inválido");

        return b.findConta(numeroConta);

    }

    public Banco solicitarBanco() {

        Banco banco = null;
        boolean executeMenuBanco = true;


        while (executeMenuBanco) {

            System.out.println("---- Informe o seu banco ----");
            System.out.println("1 - Bradesco");
            System.out.println("2 - Santander");
            System.out.println("3 - Itaú");
            System.out.print("-> ");
            int coice = scanner.nextInt();

            scanner.nextLine();

            switch (coice) {

                case 1:
                    banco = bancoRepository.buscarBanco("Bradesco");
                    executeMenuBanco = false;

                    break;

                case 2:
                    banco = bancoRepository.buscarBanco("Santander");
                    executeMenuBanco = false;

                    break;

                case 3:
                    banco = bancoRepository.buscarBanco("Itaú");
                    executeMenuBanco = false;

                    break;

                default:
                    System.out.println("Opção inválida");
                    break;

            }
        }

        return banco;
    }
}
