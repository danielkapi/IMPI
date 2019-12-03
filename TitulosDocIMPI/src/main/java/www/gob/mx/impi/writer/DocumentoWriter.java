package www.gob.mx.impi.writer;
import java.util.List;
import org.springframework.batch.item.ItemWriter;
import www.gob.mx.impi.model.Documento;


public class DocumentoWriter implements ItemWriter<Documento> {

    @Override
    public void write(List<? extends Documento> items) throws Exception {
        for (Documento doc : items) {
            System.out.println("Titulo a guardar en BD "+ doc.getTitulo());
        }
    }

}
