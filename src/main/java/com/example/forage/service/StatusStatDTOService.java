package com.example.forage.service;

import com.example.forage.dto.StatusStatDTO;
import com.example.forage.model.Client;
import com.example.forage.model.Demande;
import com.example.forage.model.DemandeStatus;
import com.example.forage.repository.ClientRepository;
import com.example.forage.repository.DemandeRepository;
import com.example.forage.repository.DemandeStatusRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatusStatDTOService {

    // private final ClientRepository clientRepository;
    private final DemandeRepository demandeRepository;
    private final DemandeStatusService demandeStatusService;


    public StatusStatDTOService(DemandeRepository demandeRepository,DemandeStatusService demandeStatusService) {
        this.demandeRepository = demandeRepository;
        this.demandeStatusService = demandeStatusService;
    }
    public List<StatusStatDTO> getStatByStatus() {

        List<Demande> demandes = demandeRepository.findAll();

        Map<Integer, StatusStatDTO> map = new HashMap<>();

        for (Demande d : demandes) {

            DemandeStatus current = demandeStatusService.getCurrentStatus(d.getId());

            if (current != null) {

                Integer id = current.getStatus().getId();
                String lib = current.getStatus().getLibelle();

                map.putIfAbsent(id, new StatusStatDTO());
                StatusStatDTO dto = map.get(id);

                dto.setId(id);
                dto.setLibelle(lib);
                dto.setCount(dto.getCount() == null ? 1 : dto.getCount() + 1);
            }
        }

        return new ArrayList<>(map.values());
    }
}