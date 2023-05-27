package com.example.sirTalion.config;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;

import com.example.sirTalion.entities.Battery;
import com.example.sirTalion.entities.Category;
import com.example.sirTalion.entities.Cpu;
import com.example.sirTalion.entities.Display;
import com.example.sirTalion.entities.Drive;
import com.example.sirTalion.entities.GraphicCard;
import com.example.sirTalion.entities.Laptop;
import com.example.sirTalion.entities.Manufacturer;
import com.example.sirTalion.entities.OperatingSystem;
import com.example.sirTalion.entities.Product;
import com.example.sirTalion.entities.Ram;
import com.example.sirTalion.entities.enumerate.DisplayType;
import com.example.sirTalion.entities.enumerate.DriveType;
import com.example.sirTalion.entities.enumerate.GraphicCardType;
import com.example.sirTalion.entities.enumerate.PanelType;
import com.example.sirTalion.entities.enumerate.RamType;
import com.example.sirTalion.repository.BatteryRepository;
import com.example.sirTalion.repository.CategoryRepository;
import com.example.sirTalion.repository.CpuRepository;
import com.example.sirTalion.repository.DisplayRepository;
import com.example.sirTalion.repository.DriveRepository;
import com.example.sirTalion.repository.GraphicCardRepository;
import com.example.sirTalion.repository.LaptopRepository;
import com.example.sirTalion.repository.ManufacturerRepository;
import com.example.sirTalion.repository.OperatingSystemRepository;
import com.example.sirTalion.repository.ProductRepository;
import com.example.sirTalion.repository.RamRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @Component
// public class Test 
// {
//     @Autowired
//     ProductRepository productRepository;
    
//     @Autowired
//     ManufacturerRepository manufacturerRepository;

//     @Autowired
//     CpuRepository cpuRepository;

//     @Autowired
//     LaptopRepository laptopRepository;

//     @Autowired
//     CategoryRepository categoryRepository;

//     @Autowired
//     DisplayRepository displayRepository;

//     @Autowired
//     RamRepository ramRepository;

//     @Autowired
//     DriveRepository driveRepository;

//     @Autowired
//     GraphicCardRepository graphicCardRepository;

//     @Autowired
//     OperatingSystemRepository operatingSystemRepository;

//     @Autowired
//     BatteryRepository batteryRepository;

//     @PostConstruct
//     private void postConstruct()
//     {
//         Manufacturer manufacturer = new Manufacturer();
//         manufacturer.setName("AMD");
//         manufacturerRepository.save(manufacturer);

//         Manufacturer manufacturer1 = new Manufacturer();
//         manufacturer1.setName("KingSton");
//         manufacturerRepository.save(manufacturer1);

//         Manufacturer manufacturer2 = new Manufacturer();
//         manufacturer2.setName("NVIDIA");
//         manufacturerRepository.save(manufacturer2);
//         //----------------------------------------

//         Cpu cpu = new Cpu();
//         cpu.setCore((short)6);
//         cpu.setThread((short)6);
//         cpu.setManufacturer(manufacturer);
//         cpu.setModel("AMD Ryzen™ 5 5600X");
//         cpu.setMaxProcessorSpeed((short)4);
//         cpu.setProcessorSpeed((short)3);
//         // cpuRepository.save(cpu);
        
//         Display display = new Display();
//         display.setSize(15.4f);
//         display.setResolution("1920x1080");
//         display.setDisplayType(DisplayType.LED);
//         display.setPanelType(PanelType.IPS);
//         displayRepository.save(display);

//         Ram ram = new Ram();
//         ram.setSize((short)8);
//         ram.setModel("RAM Kingston HyperX Fury");
//         ram.setBus((short)1600);
//         ram.setRamType(RamType.DDR3);
//         ram.setManufacturer(manufacturer1);
//         ramRepository.save(ram);

//         Drive drive = new Drive();
//         drive.setModel("SSD Kingston NOW A400");
//         drive.setSize((short)240);
//         drive.setDriveType(DriveType.SSD);
//         driveRepository.save(drive);

//         GraphicCard graphicCard = new GraphicCard();
//         graphicCard.setModel("GeForce GTX 1650");
//         graphicCard.setMemory((short)8);
//         graphicCard.setManufacturer(manufacturer2);
//         graphicCard.setGraphicCardType(GraphicCardType.Integrated);
//         graphicCardRepository.save(graphicCard);

//         OperatingSystem operatingSystem = new OperatingSystem();
//         operatingSystem.setName("Windows 11");
//         operatingSystem.setType((byte)64);
//         operatingSystemRepository.save(operatingSystem);

//         Battery battery = new Battery();
//         battery.setModel("Lithium-ion");
//         battery.setVoltages(3.6f);
//         batteryRepository.save(battery);

//         List<Cpu> cpus = new ArrayList<>();
//         cpus.add(cpu);
//         List<Ram> rams = new ArrayList<>();
//         rams.add(ram);
//         List<Drive> drives = new ArrayList<>();
//         drives.add(drive);
//         List<GraphicCard> graphicCards = new ArrayList<>();
//         graphicCards.add(graphicCard);
//         Laptop laptop = new Laptop();
//         laptop.setCpus(cpus);
//         laptop.setRams(rams);
//         laptop.setDrives(drives);
//         laptop.setGraphicCards(graphicCards);
//         laptop.setManufacturer(manufacturer);
//         laptop.setName("Acer Nitro 5");
//         laptop.setWeight(2.5f);
//         laptop.setDisplay(display);
//         laptop.setOperatingSystem(operatingSystem);
//         laptop.setBattery(battery);
//         laptop.setPortInfo("1 HDMI\n1 Jack 3.5 mm\n1 Type C\n1 USB 2.0\n2 USB 3.0");
//         // laptopRepository.save(laptop);
        
//         Category category = new Category();
//         category.setName("laptop");
//         categoryRepository.save(category);

//         Product product = new Product();
//         product.setCategory(category);
//         product.setLaptop(laptop);
//         product.setPrice(10000000);
    
//         System.out.println("----------------------------------------------------------------------Cool-----------------------------------------------");
//         productRepository.save(product);
//         product = productRepository.findById(1);
//         cpu = product.getLaptop().getCpus().get(0);
//         System.out.println(cpu.getModel());
//         cpu.setCore((short)2);
//         System.out.println("----------------------------------------------------------------------Cool-----------------------------------------------");
//         productRepository.saveAndFlush(product);
//         System.out.println("----------------------------------------------------------------------Cool-----------------------------------------------");
//     }
// }

@RestController
@RequestMapping("/test")
class Test1
{
    @Autowired
    ProductRepository productRepository;

    @Autowired
    LaptopRepository laptopRepository;

    @Autowired
    DisplayRepository displayRepository;

    @GetMapping("/product")
    public Product getProduct() 
    {
        return productRepository.findById(1L);
    }

    @PutMapping("/product")
    public void saveOrUpdateProduct(@RequestBody @Valid Product product)
    {
        productRepository.save(product);
    }

    @GetMapping("/laptop")
    public Laptop getLaptop() 
    {
        return laptopRepository.getReferenceById(1L);
    }

    @PutMapping("/laptop")
    public void saveOrUpdateLaptop(@RequestBody @Valid Laptop laptop)
    {
        laptopRepository.save(laptop);
    }

    @PutMapping("/display")
    public void saveOrUpdateDisplay(@RequestBody @Valid Display display)
    {
        displayRepository.save(display);
    }
}