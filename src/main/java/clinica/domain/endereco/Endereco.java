package clinica.domain.endereco;

import jakarta.persistence.Embeddable;

/**
 * @author Edson da Silva Freitas
 * {@code @created} 18/05/2023
 * {@code @project} api
 */
@Embeddable
//Essa classe foi criada convertendo atributos JSON, não apliquei LOMBOK para fins de estudos
public class Endereco {
    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private String numero;
    private String complemento;

    /**
     * No args constructor for use in serialization
     */
    public Endereco() {
    }

    /**
     * @param uf
     * @param cidade
     * @param complemento
     * @param numero
     * @param logradouro
     * @param bairro
     * @param cep
     */
    public Endereco(String logradouro, String bairro, String cep, String cidade, String uf, String numero, String complemento) {
        super();
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.uf = uf;
        this.numero = numero;
        this.complemento = complemento;
    }

    public Endereco(DadosEndereco dados) {
        this.logradouro = dados.logradouro();
        this.bairro = dados.bairro();
        this.cep = dados.cep();
        this.cidade = dados.cidade();
        this.uf = dados.uf();
        this.numero = dados.numero();
        this.complemento = dados.complemento();
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Endereco.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("logradouro");
        sb.append('=');
        sb.append(((this.logradouro == null) ? "<null>" : this.logradouro));
        sb.append(',');
        sb.append("bairro");
        sb.append('=');
        sb.append(((this.bairro == null) ? "<null>" : this.bairro));
        sb.append(',');
        sb.append("cep");
        sb.append('=');
        sb.append(((this.cep == null) ? "<null>" : this.cep));
        sb.append(',');
        sb.append("cidade");
        sb.append('=');
        sb.append(((this.cidade == null) ? "<null>" : this.cidade));
        sb.append(',');
        sb.append("uf");
        sb.append('=');
        sb.append(((this.uf == null) ? "<null>" : this.uf));
        sb.append(',');
        sb.append("numero");
        sb.append('=');
        sb.append(((this.numero == null) ? "<null>" : this.numero));
        sb.append(',');
        sb.append("complemento");
        sb.append('=');
        sb.append(((this.complemento == null) ? "<null>" : this.complemento));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result * 31) + ((this.uf == null) ? 0 : this.uf.hashCode()));
        result = ((result * 31) + ((this.cidade == null) ? 0 : this.cidade.hashCode()));
        result = ((result * 31) + ((this.complemento == null) ? 0 : this.complemento.hashCode()));
        result = ((result * 31) + ((this.numero == null) ? 0 : this.numero.hashCode()));
        result = ((result * 31) + ((this.logradouro == null) ? 0 : this.logradouro.hashCode()));
        result = ((result * 31) + ((this.bairro == null) ? 0 : this.bairro.hashCode()));
        result = ((result * 31) + ((this.cep == null) ? 0 : this.cep.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Endereco) == false) {
            return false;
        }
        Endereco rhs = ((Endereco) other);
        return (((((((((this.uf == rhs.uf) || ((this.uf != null) && this.uf.equals(rhs.uf))) && ((this.cidade == rhs.cidade) || ((this.cidade != null) && this.cidade.equals(rhs.cidade)))) && ((this.complemento == rhs.complemento) || ((this.complemento != null) && this.complemento.equals(rhs.complemento)))) && ((this.numero == rhs.numero) || ((this.numero != null) && this.numero.equals(rhs.numero)))) && ((this.logradouro == rhs.logradouro) || ((this.logradouro != null) && this.logradouro.equals(rhs.logradouro)))) && ((this.bairro == rhs.bairro) || ((this.bairro != null) && this.bairro.equals(rhs.bairro)))) && ((this.cep == rhs.cep) || ((this.cep != null) && this.cep.equals(rhs.cep)))));
    }

    public void atualizarInformacoes(DadosEndereco dados) {
       if (dados.logradouro() != null) this.logradouro = dados.logradouro();
        if (dados.bairro() != null) this.bairro = dados.bairro();
        if (dados.cep() != null) this.cep = dados.cep();
        if (dados.cidade() != null) this.cidade = dados.cidade();
        if (dados.uf() != null) this.uf = dados.uf();
        if (dados.numero() != null) this.numero = dados.numero();
        if (dados.complemento() != null) this.complemento = dados.complemento();
    }
}