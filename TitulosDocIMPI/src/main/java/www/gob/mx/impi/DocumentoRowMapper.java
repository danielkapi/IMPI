package www.gob.mx.impi;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import www.gob.mx.impi.model.Documento;

public class DocumentoRowMapper implements RowMapper<Documento> {

	@Override
	public Documento mapRow(ResultSet rs, int rowNum) throws SQLException {

		Documento doc = new Documento();
		doc.setId(rs.getInt("id"));
		doc.setDocumentoElectronico(rs.getBytes("documento") == null ? null: rs.getBytes("documento"));
		doc.setFechaRegistro(rs.getDate("fecha_registro") == null ? null: rs.getDate("fecha_registro"));
		return doc;
	}
}
