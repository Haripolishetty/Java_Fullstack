package com.mnc.controller;
import com.mnc.entity.Audit;
import com.mnc.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/audits")
@CrossOrigin(origins = "*")
public class AuditController {
   @Autowired
   private AuditService auditService;
   @GetMapping
   public ResponseEntity<List<Audit>> getAllAudits() {
       return ResponseEntity.ok(auditService.getAllAudits());
   }
   @GetMapping("/{id}")
   public ResponseEntity<Audit> getAuditById(@PathVariable Long id) {
       return auditService.getAuditById(id)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
   }
   @GetMapping("/entity")
   public ResponseEntity<Map<String, Object>> getAuditsByEntity(
           @RequestParam String entityName,
           @RequestParam(defaultValue = "0") int page,
           @RequestParam(defaultValue = "10") int size) {
       Pageable pageable = PageRequest.of(page, size);
       Page<Audit> auditPage = auditService.getAuditsByEntityName(entityName, pageable);
       Map<String, Object> response = new HashMap<>();
       response.put("audits", auditPage.getContent());
       response.put("currentPage", auditPage.getNumber());
       response.put("totalItems", auditPage.getTotalElements());
       response.put("totalPages", auditPage.getTotalPages());
       return ResponseEntity.ok(response);
   }
   @GetMapping("/entity/{entityName}/{entityId}")
   public ResponseEntity<List<Audit>> getAuditsByEntityAndId(
           @PathVariable String entityName,
           @PathVariable Long entityId) {
       return ResponseEntity.ok(auditService.getAuditsByEntity(entityName, entityId));
   }
   @GetMapping("/user/{username}")
   public ResponseEntity<List<Audit>> getAuditsByUser(@PathVariable String username) {
       return ResponseEntity.ok(auditService.getAuditsByUser(username));
   }
   @GetMapping("/action/{action}")
   public ResponseEntity<List<Audit>> getAuditsByAction(@PathVariable String action) {
       return ResponseEntity.ok(auditService.getAuditsByAction(action));
   }
}