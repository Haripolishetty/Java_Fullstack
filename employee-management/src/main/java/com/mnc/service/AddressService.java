package com.mnc.service;
import com.mnc.entity.Address;
import com.mnc.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class AddressService {
   @Autowired
   private AddressRepository addressRepository;
   public List<Address> getAllAddresses() {
       return addressRepository.findAll();
   }
   public Optional<Address> getAddressById(Long id) {
       return addressRepository.findById(id);
   }
   public List<Address> getAddressesByCity(String city) {
       return addressRepository.findByCity(city);
   }
   public List<Address> getAddressesByState(String state) {
       return addressRepository.findByState(state);
   }
   public List<Address> getAddressesByCountry(String country) {
       return addressRepository.findByCountry(country);
   }
   public Address createAddress(Address address) {
       return addressRepository.save(address);
   }
   public Address updateAddress(Long id, Address addressDetails) {
       Address address = addressRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Address not found with id: " + id));
       address.setStreet(addressDetails.getStreet());
       address.setCity(addressDetails.getCity());
       address.setState(addressDetails.getState());
       address.setZipCode(addressDetails.getZipCode());
       address.setCountry(addressDetails.getCountry());
       return addressRepository.save(address);
   }
   public void deleteAddress(Long id) {
       addressRepository.deleteById(id);
   }
}