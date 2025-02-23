import java.text.DateFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProdutoPerecivel extends Produto {

    private static double DESCONTO = 0.25;
    private static int PRAZO_DESCONTO = 7;
    private LocalDate dataDeValidade;

    public ProdutoPerecivel(String desc, double precoCusto, double margemLucro, LocalDate dataDeValidade){
        super(desc, precoCusto, margemLucro);
        if(dataDeValidade.isBefore(LocalDate.now()))
        throw new IllegalArgumentException("Validade anterior ao dia de hoje.");
        this.dataDeValidade = dataDeValidade;
    }

    @Override
    public double valorDeVenda() {
        double desconto = 0d;
        int diasValidade = LocalDate.now().until(dataDeValidade).getDays();
        if(diasValidade <= PRAZO_DESCONTO)
        desconto = DESCONTO;

        return precoCusto * (1+margemLucro) * (1-DESCONTO);
    }

    @Override
    public String toString(){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/mm/yyyy");
        String dados = super.toString();
        dados += " - Valido ate " + formato.format(dataDeValidade);
        return dados;

    }
    
}
