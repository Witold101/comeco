package by.vistar.comeco.model.services;

import by.vistar.comeco.model.entity.Bank;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class ServiceBankTest {


    static Connection connection;

    @AfterClass
    public static void connectionClose(){
        new ServiceTablesInitDrop(connection).dropTable();
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @BeforeClass
    public static void initTest() {
        connection = ServiceMainTest.getConnection();
        new ServiceTablesInitDrop(connection).initTable();
    }

    @Test
    public void add() {
        Bank bank = new Bank();
        bank.setName(" Закрытое акционерное общество РРБ Банк  ");
        bank.setSwift(" RRTTOOKKHH  ");
        bank.setRegionNumber(" 1235456780  ");
        bank.setAddressId(0l);

        new ServiceBank(connection).add(bank);
        Bank bankTest = new ServiceBank(connection).get(bank.getId());

        assertEquals(bank.getName(),bankTest.getName());
        assertEquals(bank.getSwift(),bankTest.getSwift());
        assertEquals(bank.getRegionNumber(),bankTest.getRegionNumber());
        assertEquals(bank.getAddressId(),bankTest.getAddressId());
    }

    @Test
    public void get() {
        add();
    }

    @Test
    public void edit() {
        Bank bank = new Bank();
        bank.setName(" Закрытое акционерное общество РРБ Банк  ");
        bank.setSwift(" RRTTOOKKHH  ");
        bank.setRegionNumber(" 1235456780  ");
        bank.setAddressId(0l);
        new ServiceBank(connection).add(bank);
        Bank bankTest = new Bank();
        bankTest.setName(" Закрытое акционерное общество РРБ Банк Test ");
        bankTest.setSwift(" RRTTOOKKHH   Test");
        bankTest.setRegionNumber(" 1235456780  Test ");
        bankTest.setAddressId(0l);
        bankTest.setId(bank.getId());
        new ServiceBank(connection).edit(bankTest);
        bank=new ServiceBank(connection).get(bankTest.getId());

        assertEquals(bank.getName(),bankTest.getName());
        assertEquals(bank.getSwift(),bankTest.getSwift());
        assertEquals(bank.getRegionNumber(),bankTest.getRegionNumber());
        assertEquals(bank.getAddressId(),bankTest.getAddressId());
        assertEquals(bank.getId(),bankTest.getId());
    }

    @Test
    public void dell() {
        Bank bank = new Bank();
        bank.setName(" Закрытое акционерное общество РРБ Банк  ");
        bank.setSwift(" RRTTOOKKHH  ");
        bank.setRegionNumber(" 1235456780  ");
        bank.setAddressId(0l);
        new ServiceBank(connection).add(bank);
        new ServiceBank(connection).dell(bank.getId());
        assertNull(new ServiceBank(connection).get(bank.getId()));

    }
}