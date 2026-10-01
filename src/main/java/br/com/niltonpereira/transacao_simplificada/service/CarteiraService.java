package br.com.niltonpereira.transacao_simplificada.service;

import br.com.niltonpereira.transacao_simplificada.infrastructure.entity.Carteira;
import br.com.niltonpereira.transacao_simplificada.infrastructure.repository.CarteiraRepository;
import br.com.niltonpereira.transacao_simplificada.infrastructure.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class CarteiraService {

    private final CarteiraRepository carteiraRepository;

    public CarteiraService(CarteiraRepository carteiraRepository) {
        this.carteiraRepository = carteiraRepository;
    }

    public void salvar(Carteira carteira){
      carteiraRepository.save(carteira);

    }
}
