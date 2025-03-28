package ec.tcs.bankclients.application.mapper;

import ec.tcs.bankclients.application.dto.ClienteDTO;
import ec.tcs.bankclients.domain.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    ClienteDTO clientToClientDTO(Cliente client);

    Cliente clientDTOToClient(ClienteDTO clientDTO);

}
