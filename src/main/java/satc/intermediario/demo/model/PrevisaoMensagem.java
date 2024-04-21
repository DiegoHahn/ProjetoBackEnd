package satc.intermediario.demo.model;

//Essa classe serve para formatar os dados que eu recebo da API em uma mensagem para o usuário
public class PrevisaoMensagem {
    private String mensagem;

    public PrevisaoMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
