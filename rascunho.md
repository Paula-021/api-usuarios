1: fazer a branch do endereço (implementar a classe endereço)
2: fazer a branch do add endereço no usuário (feat/add-address-to-user)
-> fazer a branch feat/user-dtos
3: fazer a branch feat/viacepapi
4: implementar a classe ViaCepApiService
5: implementar a classe ViaCepApiServiceImpl
6: fazer o método findEnderecoByCep(String cep) na classe ViaCepApiService
7: pegar o endereço encontrado e associar o usuário no método addUser na controller



1: UserRequestDTO -> nome, email, cep
2: UserResponseDTO -> nome, endereco AddressResponseDTO (cep, logradouro,  bairro, cidade, estado)
//DTO -> Data Transfer Object

AddressResponseDTO -> cep, logradouro, bairro, cidade, estado


1°: o usuário mudou de endereço => pegar o cep novo => buscar o cep na viacepapi => deletar o endereço antigo do usuário => adicionar o novo endereço na tabela de endereço (PATCH)

2°: o usuário não mudou de endereço => só quer preencher as infos do endereço que faltam (ex: complemento, logradouro, etc) (PUT)