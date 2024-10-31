package school.sptech;

import school.sptech.especialistas.DesenvolvedorMobile;
import school.sptech.especialistas.DesenvolvedorWeb;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Consultoria {
    private String nome;
    private Integer vagas;
    private List<Desenvolvedor> desenvolvedores;

    public Consultoria() {
        this.desenvolvedores = new ArrayList<>();
    }

    public Consultoria(String nome, Integer vagas) {
        this.nome = nome;
        this.vagas = vagas;
        this.desenvolvedores = new ArrayList<>();
    }

    public void contratar(Desenvolvedor desenvolvedor) {
        if (vagas > desenvolvedores.size() && desenvolvedor != null) {
            desenvolvedores.add(desenvolvedor);
        }
    }

    public void contratarFullstack(DesenvolvedorWeb desenvolvedor) {
        if (vagas > desenvolvedores.size() && desenvolvedor.isFullstack()) {
            desenvolvedores.add(desenvolvedor);
        }
    }

    public Double getTotalSalarios() {
        Double total = 0.0;
        for (Desenvolvedor dev : desenvolvedores) {
            total += dev.calcularSalario();
        }
        return total;
    }

    public Integer qtdDesenvolvedoresMobile() {
        return desenvolvedores.stream().filter(dev -> dev instanceof DesenvolvedorMobile).toList().size();
    }

    public List<Desenvolvedor> buscarPorSalarioMaiorIgualQue(Double salario) {
        return desenvolvedores.stream().filter(dev -> dev.calcularSalario() >= salario).toList();
    }

    public Desenvolvedor buscarMenorSalario() {
        if (desenvolvedores.isEmpty()) {
            return null;
        }
        return desenvolvedores.stream().sorted(Comparator.comparingDouble(Desenvolvedor::calcularSalario)).findFirst().orElse(null);
    }

    public List<Desenvolvedor> buscarPorTecnologia(String tecnologia) {
        return desenvolvedores.stream()
                .filter(dev -> (dev instanceof DesenvolvedorWeb && (
                            ((DesenvolvedorWeb) dev).getSgbd().equalsIgnoreCase(tecnologia) ||
                            ((DesenvolvedorWeb) dev).getFrontend().equalsIgnoreCase(tecnologia) ||
                            ((DesenvolvedorWeb) dev).getBackend().equalsIgnoreCase(tecnologia)
                        ) ||
                        (dev instanceof DesenvolvedorMobile && (
                            ((DesenvolvedorMobile) dev).getPlataforma().equalsIgnoreCase(tecnologia) ||
                            ((DesenvolvedorMobile) dev).getLinguagem().equalsIgnoreCase(tecnologia)
                        )))).toList();

    }

    public Double getTotalSalariosPorTecnologia(String tecnologia) {
        List<Desenvolvedor> desenvolvedoresTecnologia = buscarPorTecnologia(tecnologia);

        Double total = 0.0;
        for (Desenvolvedor dev : desenvolvedoresTecnologia) {
            total += dev.calcularSalario();
        }
        return total;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getVagas() {
        return vagas;
    }

    public void setVagas(Integer vagas) {
        this.vagas = vagas;
    }

}
