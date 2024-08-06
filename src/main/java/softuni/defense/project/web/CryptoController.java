package softuni.defense.project.web;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import softuni.defense.project.model.entities.CoinPriceEntity;
import softuni.defense.project.service.CryptoService;

import java.util.List;

@Controller
@RequestMapping("/crypto")
@CrossOrigin(origins = "http://localhost:3000")
public class CryptoController {

    private final CryptoService cryptoService;

    public CryptoController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<CoinPriceEntity>> getAllCryptoEntities() {
        return ResponseEntity.ok(this.cryptoService.getRippleAndBitcoinData());
    }
}
