import bd.daos.CadastroDaos;
import bd.dbos.CadastroDbo;
import webservice.ClienteWS;
import webservice.Logradouro;

public class Programa {


    public static void main(String[] args) throws Exception {

        //variavel para parar o whiile e encerrar o programa
        int encerrar = 1;
        while (encerrar == 1) {

        //variaveis para utilizar a classe teclado e guardar as informaçoes do usuario

                String nomeT;
                String cpfT;
                String cepT;
                String complementoT;
                int numeroCasaT;

                System.out.println("bem vindo ao cadastro!!");
                System.out.println("digite a opçao desejada: " +
                        "\ndigite 1 caso deseje se cadastrar:" +
                        "\nDigite 2 caso deseje excluir seu cadastro: " +
                        "\nDigite 3 caso deseje atualizar seu o cadastro:" +
                        "\nDigite 4  caso deseje consultar seu cadastro:");
                int n = Teclado.getUmInt();


                switch (n) {


                    case 1:

                        // incluindo o cadastro (create)
                        System.out.println("digite seu cpf");
                        cpfT = Teclado.getUmString();

                        System.out.println("digite seu nome");
                        nomeT = Teclado.getUmString();

                        System.out.println("digite seu cep");
                        cepT = Teclado.getUmString();

                        System.out.println("digite o complemento ");
                        complementoT = Teclado.getUmString();

                        System.out.println("digite o numero da casa");
                        numeroCasaT = Teclado.getUmInt();


                        CadastroDaos.incluir(new CadastroDbo(cpfT, nomeT, cepT,
                                complementoT, numeroCasaT));

                        break;

                    case 2:

                        // excluindo o cadastro pelo cpf (Delete)

                        System.out.println("digite seu cpf");
                        cpfT = Teclado.getUmString();


                        CadastroDaos.excluir(cpfT);

                        break;
                    case 3:

                        // alterando informaçoes ja cadastradas (UPDATE)

                        System.out.println("Selecione o que deseja alterar.");
                        System.out.println("digite a opçao desejada: " +
                                "\ndigite 1 caso deseje atualizar seu nome:" +
                                "\nDigite 2 caso deseje atualizar seu cpf: " +
                                "\nDigite 3 caso deseje atualizar seu Endereço:" );
                        int x = Teclado.getUmInt();

                        switch (x){

                            // alterando o nome
                            case 1:
                                System.out.println("digite seu nome");
                                nomeT = Teclado.getUmString();

                                System.out.println("digite seu cpf");
                                cpfT = Teclado.getUmString();

                                CadastroDaos.alterarNome(nomeT,cpfT);
                                break;

                             // alterando cpf
                            case 2:

                                System.out.println("digite seu cpf");
                                cpfT = Teclado.getUmString();

                                System.out.println("digite o novo cpf");
                                String nCpf = Teclado.getUmString();

                                CadastroDaos.alterarCpf(cpfT,nCpf);
                                break;

                             //alterando o endereço
                            case 3:
                                System.out.println("digite seu cep");
                                cepT = Teclado.getUmString();

                                System.out.println("digite o complemento ");
                                complementoT = Teclado.getUmString();

                                System.out.println("digite o numero da casa");
                                numeroCasaT = Teclado.getUmInt();

                                System.out.println("digite seu cpf");
                                cpfT = Teclado.getUmString();

                                CadastroDaos.alterarEndereço(cepT,complementoT,numeroCasaT,cpfT);
                                break;


                            default:
                                System.out.println("opçao invalida, tente novamente");
                        }

                        break;

                    case 4:
                        // consultando as informaçoes do usuario(Read)
                        System.out.println("digite seu cpf");
                        cpfT = Teclado.getUmString();


                        System.out.println(CadastroDaos.getCadastro(cpfT));

                        cepT = CadastroDaos.getCep(cpfT);

                        Logradouro logradouro = (Logradouro) ClienteWS.getObjeto(Logradouro.class,
                                "https://api.postmon.com.br/v1/cep", cepT);
                        System.out.println(logradouro);


                        break;

                    default:
                        System.out.println("opçao invalida!!");

                }
//
                System.out.println("Se deseja continuar digite 1 \n caso deseja encerrar digite 2");
                encerrar = Teclado.getUmInt();


        }
    }
}
