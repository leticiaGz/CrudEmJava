package bd.dbos;

public class CadastroDbo
{
    // Uma variavel para cada atributo do banco de dados
    private String  cpf;
    private String  cep;
    private String nome;
    private String complemento;
    private int numeroCasa;

    public CadastroDbo(String cpf, String nome, String cep, String complemento,int numeroCasa) throws Exception
    {
        this.setCpf (cpf);
        this.setNome(nome);
        this.setCep (cep);
        this.setComplemento(complemento);
        this.setNumeroCasa(numeroCasa);
    }


    public CadastroDbo(){ // exigencia do  mapeador do json
    }

    // um setter para cada atributo do banco
    public void setCep (String cep) throws Exception
    {
        if (cep==null || cep.isEmpty())
            throw new Exception ("CEP invalido!!");
        this.cep = cep;
    }

    public void setCpf (String cpf) throws Exception
    {
        if (cpf==null || cpf.isEmpty())
            throw new Exception ("CPF invalido!!");

        this.cpf = cpf;
    }

    public void setNome (String nome) throws Exception
    {
        if (nome==null || nome.isEmpty())
            throw new Exception ("Nome nao fornecido");

        this.nome = nome;
    }

    public void setComplemento (String complemento) throws Exception
    {
        // nao temos verificaçao pois o complemento pode ser nulo

        this.complemento = complemento;
    }

    public void setNumeroCasa (int numeroCasa) throws Exception
    {
        if (numeroCasa <= 0)
            throw new Exception ("Numero invalido!!");

        this.numeroCasa = numeroCasa;
    }

    // um gettter para cada atributo do banco
    public String getCep ()
    {
        return this.cep;
    }

    public String getNome ()
    {
        return this.nome;
    }

    public String getComplemento ()
    {
        return this.complemento;
    }

    public int getNumeroCasa ()
    {
        return this.numeroCasa;
    }

    public String getCpf ()
    {
        return this.cpf;
    }

    // metodos obrigatorios
    public String toString ()
    {

         return "CPF..........: "+this.cpf+"\n"+
         "Nome.........: "+this.nome  +"\n"+
         "Complemento..: "+this.complemento  +"\n"+
         "Numero casa..: "+this.numeroCasa;



    }
    public boolean equals (Object obj)
    {
        if (this==obj)
            return true;

        if (obj==null)
            return false;

        if (!(obj instanceof CadastroDbo))
            return false;

        CadastroDbo cad = (CadastroDbo)obj;

        if (this.cpf.equals(cad.cpf))
            return false;

        if (this.nome.equals(cad.nome))
            return false;

        if (this.cep!=cad.cep)
            return false;

        if (this.complemento.equals(cad.complemento))
            return false;

        if (this.numeroCasa!=cad.numeroCasa)
            return false;

        return true;
    }

    public int hashCode ()
    {
        int ret=666;

        ret = 7*ret + this.cep.hashCode();
        ret = 7*ret + this.nome.hashCode();
        ret = 7*ret + this.complemento.hashCode();
        ret = 7*ret + this.cpf.hashCode();
        ret = 7*ret + new Integer(this.numeroCasa).hashCode();

        return ret;
    }

}