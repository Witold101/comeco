package by.vistar.comeco.model.db;

public class DbConstants {

    public static final String TABLE_NAME_PHONES = "phones";
    public static final String TABLE_NAME_PARTNERS = "partners";
    public static final String TABLE_NAME_ADDRESSES = "addresses";
    public static final String TABLE_NAME_COUNTRY = "country";
    public static final String TABLE_NAME_ACCOUNTS = "accounts";
    public static final String TABLE_NAME_BANKS = "banks";
    public static final String TABLE_NAME_BANK_ADDRESS = "bank_address";


    //-------------------------------- ACCOUNT ---------------------------------------------
    public static final Integer MAX_ACCOUNT_LENGTH = 25;
    public static final Integer MAX_ACCOUNT_TYPE_TEXT = 50;

    public static final String MYSQL_ADD_ACCOUNT = "INSERT INTO " + TABLE_NAME_ACCOUNTS +
            " (`account`,`bank_id`, `partners_id`, `type_text`, `type`) VALUE (?,?,?,?,?)";
    public static final String MYSQL_GET_ACCOUNT = "SELECT * FROM " + TABLE_NAME_ACCOUNTS + " WHERE id=?;";
    public static final String MYSQL_DELL_ACCOUNT = "DELETE FROM " + TABLE_NAME_ACCOUNTS + " WHERE id=?;";
    public static final String MYSQL_EDIT_ACCOUNT = "UPDATE " + TABLE_NAME_ACCOUNTS +
            " SET account=?, bank_id=?, partners_id=?, type_text=?, type=?  WHERE id=?;";

    //-------------------------------- BANK ADDRESS ----------------------------------------
    public static final String MYSQL_ADD_BANK_ADDRESS = "INSERT INTO " + TABLE_NAME_BANK_ADDRESS +
            " (`country_id`,`post_code`, `region`, `town`, `street`,`building`) VALUE (?,?,?,?,?,?)";
    public static final String MYSQL_GET_BANK_ADDRESS = "SELECT * FROM " + TABLE_NAME_BANK_ADDRESS + " WHERE id=?;";
    public static final String MYSQL_DELL_BANK_ADDRESS = "DELETE FROM " + TABLE_NAME_BANK_ADDRESS + " WHERE id=?;";
    public static final String MYSQL_EDIT_BANK_ADDRESS = "UPDATE " + TABLE_NAME_BANK_ADDRESS +
            " SET country_id=?, post_code=?, region=?, town=?, street=?, building=?  WHERE id=?;";


    //--------------------------------- BANK ------------------------------------------------
    public static final Integer MAX_BANK_NAME = 100;
    public static final Integer MAX_BANK_SWIFT = 20;
    public static final Integer MAX_BANK_NUMBER = 20;

    public static final String MYSQL_ADD_BANK = "INSERT INTO " + TABLE_NAME_BANKS +
            " (`name`,`swift`,`region_number`,`address_id`) VALUE (?,?,?,?)";
    public static final String MYSQL_GET_BANK = "SELECT * FROM " + TABLE_NAME_BANKS + " WHERE id=?;";
    public static final String MYSQL_DELL_BANK = "DELETE FROM " + TABLE_NAME_BANKS + " WHERE id=?;";
    public static final String MYSQL_EDIT_BANK = "UPDATE " + TABLE_NAME_BANKS +
            " SET name=?, swift=?, region_number=?, address_id=? WHERE id=?;";

    //--------------------------------- ADDRESS ----------------------------------------------
    public static final Integer MAX_ADDRESS_POST = 20;
    public static final Integer MAX_ADDRESS_REGION = 100;
    public static final Integer MAX_ADDRESS_TOWN = 100;
    public static final Integer MAX_ADDRESS_STREET = 100;
    public static final Integer MAX_ADDRESS_BUILDING = 20;


    public static final String MYSQL_ADD_ADDRESS = "INSERT INTO " + TABLE_NAME_ADDRESSES +
            " (`country_id`,`post_code`,`region`,`town`,`street`,`building`,`partner_id`,`type`) VALUE (?,?,?,?,?,?,?,?)";
    public static final String MYSQL_GET_ADDRESS = "SELECT * FROM " + TABLE_NAME_ADDRESSES + " WHERE id=?;";
    public static final String MYSQL_DELL_ADDRESS = "DELETE FROM " + TABLE_NAME_ADDRESSES + " WHERE id=?;";
    public static final String MYSQL_EDIT_ADDRESS = "UPDATE " + TABLE_NAME_ADDRESSES +
            " SET country_id=?, post_code=?, region=?, town=?, street=?, building=?, partner_id=?, type=? WHERE id=?;";

    //--------------------------------- COUNTRY ----------------------------------------------
    public static final Integer MAX_COUNTRY_NAME = 50;
    public static final Integer MAX_COUNTRY_FULL_NAME = 150;
    public static final Integer MAX_COUNTRY_COD = 5;
    public static final Integer MAX_COUNTRY_PATH_FLAG = 150;

    public static final String MYSQL_ADD_COUNTRY = "INSERT INTO " + TABLE_NAME_COUNTRY +
            " (name, full_name, cod_letters,path_flag, phone_code) VALUE (?,?,?,?,?)";
    public static final String MYSQL_GET_COUNTRY = "SELECT * FROM " + TABLE_NAME_COUNTRY + " WHERE id=?;";
    public static final String MYSQL_DELL_COUNTRY = "DELETE FROM " + TABLE_NAME_COUNTRY + " WHERE id=?;";
    public static final String MYSQL_EDIT_COUNTRY = "UPDATE " + TABLE_NAME_COUNTRY + " SET name=?, " +
            "full_name=?, cod_letters=?, path_flag=?, phone_code=?  WHERE id=?;";

    //--------------------------------- PHONES -----------------------------------------------
    public static final Integer MAX_PHONE_NUMBER = 50;

    public static final String MYSQL_ADD_PHONE = "INSERT INTO " + TABLE_NAME_PHONES +
            " (number, type, partner_id) VALUE (?,?,?)";
    public static final String MYSQL_GET_PHONE = "SELECT * FROM " + TABLE_NAME_PHONES + " WHERE id=?;";
    public static final String MYSQL_DELL_PHONE = "DELETE FROM " + TABLE_NAME_PHONES + " WHERE id=?;";
    public static final String MYSQL_EDIT_PHONE = "UPDATE " + TABLE_NAME_PHONES + " SET number=?, " +
            "type=?, partner_id=?  WHERE id=?;";


    //-------------------------------- PARTNERS ----------------------------------------------
    public static final Integer MAX_PARTNER_NAME = 70;
    public static final Integer MAX_PARTNER_FULL_NAME = 150;
    public static final Integer MAX_PARTNER_VAT_NUMBER = 50;
    public static final Integer MAX_PARTNER_PERSONAL_NUMBER = 50;
    public static final Integer MAX_PARTNER_EMAIL = 30;
    public static final Integer MAX_PARTNER_INFO = 250;

    public static final String MYSQL_ADD_PARTNER = "INSERT INTO " + TABLE_NAME_PARTNERS +
            " (name, full_name, vat_number, personal_number, email, info) VALUE (?,?,?,?,?,?)";
    public static final String MYSQL_DELL_PARTNER = "DELETE FROM " + TABLE_NAME_PARTNERS + " where id=?;";
    public static final String MYSQL_EDIT_PARTNER = "UPDATE " + TABLE_NAME_PARTNERS + " SET name=?, " +
            "full_name=?, vat_number=?, personal_number=?, email=?, info=?  WHERE id=?;";
    public static final String MYSQL_GET_PARTNER = "SELECT * FROM " + TABLE_NAME_PARTNERS + " WHERE id=?;";

    //---------------------------INIT TABLES--------------------------------------------------
    public static final String MYSQL_NEW_TABLE_PARTNERS =
            "CREATE TABLE IF NOT EXISTS `" + TABLE_NAME_PARTNERS +
                    "` (`id` INT(11) NOT NULL AUTO_INCREMENT, `name` VARCHAR(" + MAX_PARTNER_NAME + ") NOT NULL," +
                    "`full_name` VARCHAR(" + MAX_PARTNER_FULL_NAME + ") NULL DEFAULT NULL,`vat_number` VARCHAR(" + MAX_PARTNER_VAT_NUMBER + ")" +
                    " NULL DEFAULT NULL,`personal_number` VARCHAR(" + MAX_PARTNER_PERSONAL_NUMBER + ") NULL DEFAULT NULL," +
                    " `email` VARCHAR(" + MAX_PARTNER_EMAIL + ") NULL DEFAULT NULL,`info` VARCHAR(" + MAX_PARTNER_INFO + ") NULL DEFAULT NULL," +
                    " PRIMARY KEY (`id`)) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;";

    public static final String MYSQL_NEW_TABLE_ADDRESSES =
            " CREATE TABLE IF NOT EXISTS `" + TABLE_NAME_ADDRESSES + "` (`id` INT(11)" +
                    " NOT NULL AUTO_INCREMENT, `country_id` INT(11) NOT NULL,`post_code` VARCHAR(" + MAX_ADDRESS_POST + ")" +
                    " NOT NULL,`region` VARCHAR(" + MAX_ADDRESS_REGION + ") NULL DEFAULT NULL,`town` VARCHAR(" + MAX_ADDRESS_TOWN + ")" +
                    " NULL DEFAULT NULL,`street` VARCHAR(" + MAX_ADDRESS_STREET + ") NULL DEFAULT NULL,`building` VARCHAR(" + MAX_ADDRESS_BUILDING + ")" +
                    " NULL DEFAULT NULL, `partner_id` INT(11) NOT NULL,`type` INT(11) NOT NULL" +
                    " DEFAULT 0,PRIMARY KEY (`id`), INDEX `fk_country_idx` (`country_id` ASC)," +
                    " INDEX `fk_partner_address_idx` (`partner_id` ASC), CONSTRAINT `fk_country`" +
                    " FOREIGN KEY (`country_id`) REFERENCES `" + TABLE_NAME_COUNTRY + "` (`id`)" +
                    "ON DELETE NO ACTION ON UPDATE NO ACTION, CONSTRAINT `fk_partner_address`" +
                    " FOREIGN KEY (`partner_id`) REFERENCES `" + TABLE_NAME_PARTNERS +
                    "` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION) ENGINE = InnoDB" +
                    " DEFAULT CHARACTER SET = utf8;";

    public static final String MYSQL_NEW_TABLE_COUNTRY =
            "CREATE TABLE IF NOT EXISTS `" + TABLE_NAME_COUNTRY + "` (`id` INT(11) NOT NULL " +
                    "AUTO_INCREMENT,`name` VARCHAR(" + MAX_COUNTRY_NAME + ") NOT NULL,`full_name` VARCHAR(" + MAX_COUNTRY_FULL_NAME + ")" +
                    " NULL DEFAULT NULL,`cod_letters` VARCHAR(" + MAX_COUNTRY_COD + ") NOT NULL,`path_flag` VARCHAR(" + MAX_COUNTRY_PATH_FLAG + ")" +
                    " NULL DEFAULT NULL, `phone_code` INT(11) NOT NULL DEFAULT 0" +
                    ", PRIMARY KEY (`id`))ENGINE = InnoDB" +
                    " DEFAULT CHARACTER SET = utf8;";

    public static final String MYSQL_NEW_TABLE_ACCOUNTS =
            "CREATE TABLE IF NOT EXISTS `" + TABLE_NAME_ACCOUNTS + "` (`id` INT(11) NOT NULL " +
                    "AUTO_INCREMENT,`account` VARCHAR(" + MAX_ACCOUNT_LENGTH + ") NOT NULL,`bank_id` INT(11) NOT NULL," +
                    "`partners_id` INT(11) NOT NULL,`type_text` VARCHAR(" + MAX_ACCOUNT_TYPE_TEXT + ") NULL DEFAULT NULL," +
                    "`type` INT(11) NOT NULL DEFAULT 0,PRIMARY KEY (`id`),INDEX `fk_bank_idx`" +
                    " (`bank_id` ASC), INDEX `fk_partners_acc_idx` (`partners_id` ASC)," +
                    " CONSTRAINT `fk_bank` FOREIGN KEY (`bank_id`) REFERENCES `" + TABLE_NAME_BANKS +
                    "` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION, CONSTRAINT `fk_partners_acc`" +
                    " FOREIGN KEY (`partners_id`) REFERENCES `" + TABLE_NAME_PARTNERS +
                    "` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;";

    public static final String MYSQL_NEW_TABLE_BANKS =
            "CREATE TABLE IF NOT EXISTS `" + TABLE_NAME_BANKS + "` (`id` INT(11) NOT NULL AUTO_INCREMENT," +
                    " `name` VARCHAR(" + MAX_BANK_NAME + ") NOT NULL,`swift` VARCHAR(" + MAX_BANK_SWIFT + ") NOT NULL," +
                    "`region_number` VARCHAR(" + MAX_BANK_NUMBER + ") NULL DEFAULT NULL," +
                    " `address_id` INT(11) NULL DEFAULT NULL, PRIMARY KEY (`id`)," +
                    " INDEX `fk_address_bank_idx` (`address_id` ASC), CONSTRAINT `fk_address_bank`" +
                    " FOREIGN KEY (`address_id`) REFERENCES `" + TABLE_NAME_BANK_ADDRESS +
                    "` (`id`) ON DELETE SET NULL ON UPDATE SET NULL) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;";

    public static final String MYSQL_NEW_TABLE_PHONES =
            "CREATE TABLE IF NOT EXISTS `" + TABLE_NAME_PHONES + "` (`id` INT(11) NOT NULL AUTO_INCREMENT," +
                    "`number` VARCHAR(" + MAX_PHONE_NUMBER + ") NOT NULL,`partner_id` INT(11) NOT NULL,`type` INT(11)" +
                    " NOT NULL DEFAULT 0, PRIMARY KEY (`id`),INDEX `fk_partner_phone_idx` " +
                    " (`partner_id`ASC), CONSTRAINT `fk_partner_phone` FOREIGN KEY (`partner_id`)" +
                    " REFERENCES `" + TABLE_NAME_PARTNERS + "` (`id`) ON DELETE CASCADE ON UPDATE CASCADE)" +
                    " ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;";

    public static final String MYSQL_NEW_TABLE_BANK_ADDRESS =
            " CREATE TABLE IF NOT EXISTS `" + TABLE_NAME_BANK_ADDRESS + "` " +
                    "(`id` INT(11) NOT NULL AUTO_INCREMENT," +
                    " `country_id` INT(11) NOT NULL," +
                    "`post_code` VARCHAR(" + MAX_ADDRESS_POST + ") NOT NULL," +
                    "`region` VARCHAR(" + MAX_ADDRESS_REGION + ") NULL DEFAULT NULL," +
                    "`town` VARCHAR(" + MAX_ADDRESS_TOWN + ") NULL DEFAULT NULL," +
                    "`street` VARCHAR(" + MAX_ADDRESS_STREET + ") NULL DEFAULT NULL," +
                    "`building` VARCHAR(" + MAX_ADDRESS_BUILDING + ") NULL DEFAULT NULL," +
                    " PRIMARY KEY (`id`), " +
                    " INDEX `fk_country_bank_idx` (`country_id` ASC)," +
                    " CONSTRAINT `fk_country_bank` FOREIGN KEY (`country_id`)" +
                    " REFERENCES `" + TABLE_NAME_COUNTRY + "` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION)" +
                    " ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;";

}
