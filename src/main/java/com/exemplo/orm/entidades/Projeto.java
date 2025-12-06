package com.exemplo.orm.entidades;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

// entidade JPA que mapea a tabela 'projeto'

@Entity
@Table(name = "projeto")
public class Projeto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Integer codigo;
    
    @Column(name = "nome", length = 50, unique = true)
    private String nome;
    
    @Column(name = "descricao", length = 250)
    private String descricao;
    
    @Column(name = "responsavel")
    private Integer responsavel;
    
    @Column(name = "depto")
    private Integer depto;
    
    @Column(name = "data_inicio")
    @Temporal(TemporalType.DATE)
    private Date dataInicio;
    
    @Column(name = "data_fim")
    @Temporal(TemporalType.DATE)
    private Date dataFim;
    
    // relacionamento com atividade (um projeto tem várias atividades)
    @OneToMany(mappedBy = "projeto", fetch = FetchType.EAGER)
    private List<Atividade> atividades;
    
    // Construtores
    public Projeto() { // (obrigatório)
    }
    
    public Projeto(String nome, String descricao, Integer responsavel,
                   Integer depto, Date dataInicio, Date dataFim) {
        this.nome = nome;
        this.descricao = descricao;
        this.responsavel = responsavel;
        this.depto = depto;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }
    
    // Getters e Setters (obrigátorio)
    public Integer getCodigo() {
        return codigo;
    }
    
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public Integer getResponsavel() {
        return responsavel;
    }
    
    public void setResponsavel(Integer responsavel) {
        this.responsavel = responsavel;
    }
    
    public Integer getDepto() {
        return depto;
    }
    
    public void setDepto(Integer depto) {
        this.depto = depto;
    }
    
    public Date getDataInicio() {
        return dataInicio;
    }
    
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }
    
    public Date getDataFim() {
        return dataFim;
    }
    
    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }
    
    public List<Atividade> getAtividades() {
        return atividades;
    }
    
    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
    }
    
    @Override
    public String toString() {
        return "Projeto{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                ", responsavel=" + responsavel +
                '}';
    }
}