package com.helmuth.hospital.api.initialize;

import com.helmuth.hospital.api.entity.*;
import com.helmuth.hospital.api.repository.*;
import net.datafaker.Faker;
import net.datafaker.Medical;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private BuildingBlockRepository buildingBlockRepository;
    @Autowired
    private MedicalSpecialtyRepository medicalSpecialtyRepository;
    @Autowired
    private MedicalFloorRepository medicalFloorRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private NoteRepository noteRepository;

    private final int NUMBER_OF_PATIENTS = 16;
    private final int NUMBER_OF_EMPLOYEES = 8;
    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(new Locale("es"));

        MedicalSpecialty urology = MedicalSpecialty.builder().name("Urología").build();
        MedicalSpecialty surgery = MedicalSpecialty.builder().name("Cirugía").build();
        MedicalSpecialty dermatology = MedicalSpecialty.builder().name("Dermatología").build();
        MedicalSpecialty neurology = MedicalSpecialty.builder().name("Neurología").build();
        medicalSpecialtyRepository.saveAll(List.of(urology, surgery, dermatology, neurology));

        BuildingBlock buildingBlockA = BuildingBlock.builder().name("A").address(faker.address().streetAddress()).city(faker.address().city()).numberOfFloors(3).build();
        BuildingBlock buildingBlockB = BuildingBlock.builder().name("B").address(faker.address().streetAddress()).city(faker.address().city()).numberOfFloors(3).build();
        buildingBlockRepository.saveAll(List.of(buildingBlockA, buildingBlockB));

        MedicalFloor medicalFloor1A = MedicalFloor.builder().name("1A").floor(1).buildingBlock(buildingBlockA).medicalSpecialty(urology).build();
        MedicalFloor medicalFloor2A = MedicalFloor.builder().name("2A").floor(2).buildingBlock(buildingBlockA).medicalSpecialty(surgery).build();
        MedicalFloor medicalFloor1B = MedicalFloor.builder().name("1B").floor(1).buildingBlock(buildingBlockB).medicalSpecialty(dermatology).build();
        MedicalFloor medicalFloor2B = MedicalFloor.builder().name("2B").floor(2).buildingBlock(buildingBlockB).medicalSpecialty(neurology).build();
        medicalFloorRepository.saveAll(List.of(medicalFloor1A, medicalFloor2A, medicalFloor1B, medicalFloor2B));

        Room room101 = Room.builder().name("101").medicalFloor(medicalFloor1A).build();
        Room room102 = Room.builder().name("102").medicalFloor(medicalFloor1A).build();
        Room room103 = Room.builder().name("103").medicalFloor(medicalFloor1A).build();
        Room room104 = Room.builder().name("104").medicalFloor(medicalFloor1A).build();

        Room room201 = Room.builder().name("201").medicalFloor(medicalFloor2A).build();
        Room room202 = Room.builder().name("202").medicalFloor(medicalFloor2A).build();
        Room room203 = Room.builder().name("203").medicalFloor(medicalFloor2A).build();
        Room room204 = Room.builder().name("204").medicalFloor(medicalFloor2A).build();

        Room room105 = Room.builder().name("105").medicalFloor(medicalFloor1B).build();
        Room room106 = Room.builder().name("106").medicalFloor(medicalFloor1B).build();
        Room room107 = Room.builder().name("107").medicalFloor(medicalFloor1B).build();
        Room room108 = Room.builder().name("108").medicalFloor(medicalFloor1B).build();

        Room room205 = Room.builder().name("205").medicalFloor(medicalFloor2B).build();
        Room room206 = Room.builder().name("206").medicalFloor(medicalFloor2B).build();
        Room room207 = Room.builder().name("207").medicalFloor(medicalFloor2B).build();
        Room room208 = Room.builder().name("208").medicalFloor(medicalFloor2B).build();
        List<Room> rooms = List.of(room101, room102, room103, room104,
                room201, room202, room203, room204,
                room105, room106, room107, room108,
                room205, room206, room207, room208);
        roomRepository.saveAll(rooms);


        List<String> names = faker
                .collection(() -> faker.name().firstName())
                .len(NUMBER_OF_PATIENTS)
                .generate();
        List<String> lastNames = faker
                .collection(() -> faker.name().lastName())
                .len(NUMBER_OF_PATIENTS)
                .generate();

        List<Patient> patients = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_PATIENTS; i++) {
            Date hospitalitationDate = faker.date().past(365, TimeUnit.DAYS);
            Date dischargeDate = faker.date().past(180, TimeUnit.DAYS);
            Patient patient = Patient.builder()
                    .firstName(names.get(i))
                    .lastName(lastNames.get(i))
                    .dni(faker.expression("#{bothify '########?', 'true'}"))
                    .phoneNumber(faker.expression("#{numerify '6## ### ###'}"))
                    .hospitalizationDate(hospitalitationDate)
                    .dischargeDate(dischargeDate.getTime() > hospitalitationDate.getTime() ? dischargeDate : null)
                    .symptoms(faker.medical().symptoms())
                    .medicalDiagnosis(faker.medical().diseaseName())
                    .room(rooms.get(i))
                    .build();
            patients.add(patient);
        }
        patientRepository.saveAll(patients);


        List<String> employeeNames = faker
                .collection(() -> faker.name().firstName())
                .len(NUMBER_OF_EMPLOYEES)
                .generate();
        List<String> employeeLastNames = faker
                .collection(() -> faker.name().lastName())
                .len(NUMBER_OF_EMPLOYEES)
                .generate();

        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_EMPLOYEES; i++) {
            Employee employee = Employee.builder()
                    .firstName(employeeNames.get(i))
                    .lastName(employeeLastNames.get(i))
                    .dni(faker.expression("#{bothify '########?', 'true'}"))
                    .phoneNumber(faker.expression("#{numerify '6## ### ###'}"))
                    .email((employeeNames.get(i) + "." + employeeLastNames.get(i)).toLowerCase().trim() + "@helmuthhospital.com")
                    .build();
            employees.add(employee);
        }
        employeeRepository.saveAll(employees);

        List<Note> notes = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
                Note note = Note.builder()
                        .note(faker.lorem().characters(20, 250))
                        .date(faker.date().past(180, TimeUnit.DAYS))
                        .patient(patients.get((int) (Math.random() * (patients.size() - 1))))
                        .employee(employees.get((int) (Math.random() * (employees.size() - 1))))
                        .build();
                notes.add(note);
        }
        noteRepository.saveAll(notes);
    }

}
