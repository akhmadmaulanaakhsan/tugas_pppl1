import com.example.tugas1_pppl.Wallet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class WalletTest {

    Wallet myWallet = new Wallet("John Doe");

    @Test
    @DisplayName("Test scenario untuk set dan get owner")
    public void testSetAndGetOwner() {
        assertSame("John Doe", myWallet.getOwner());

        myWallet.setOwner("Jane Doe");
        assertNotSame("John Doe", myWallet.getOwner());
        assertSame("Jane Doe", myWallet.getOwner());
    }

    @Test
    @DisplayName("Test scenario untuk menambah dan mengambil kartu")
    public void testAddAndTakeCard() {
        assertTrue(myWallet.getCards().isEmpty());

        myWallet.addCard("Credit Card");
        myWallet.addCard("ID Card");
        assertEquals(2, myWallet.getCards().size());

        myWallet.takeCard("Credit Card");
        assertEquals(1, myWallet.getCards().size());
        assertTrue(myWallet.getCards().contains("ID Card"));
        assertFalse(myWallet.getCards().contains("Credit Card"));
    }

    @Test
    @DisplayName("Test scenario untuk method addMoneys(integer) dan jumlah uang kertas tiap nominalnya")
    public void testAddMoney() {
        myWallet.addMoney(100000);
        myWallet.addMoney(100000);
        myWallet.addMoney(50000);
        myWallet.addMoney(20000);
        myWallet.addMoney(10000);
        myWallet.addMoney(5000);
        myWallet.addMoney(2000);
        myWallet.addMoney(1000);
        myWallet.addMoney(3000); // This should be an invalid money type
        myWallet.addMoney(105000); // This should be an invalid money type


        assertEquals(2, myWallet.getMoneys().get(100000).intValue());
        assertEquals(1, myWallet.getMoneys().get(50000).intValue());
        assertEquals(1, myWallet.getMoneys().get(20000).intValue());
        assertEquals(1, myWallet.getMoneys().get(10000).intValue());
        assertEquals(1, myWallet.getMoneys().get(5000).intValue());
        assertEquals(1, myWallet.getMoneys().get(2000).intValue());
        assertEquals(1, myWallet.getMoneys().get(1000).intValue());
        assertNull(myWallet.getMoneys().get(3000), "Invalid money type should not be added");
        assertNull(myWallet.getMoneys().get(105000), "Invalid money type should not be added");
        assertNotNull(myWallet.getMoneys().get(1000), "uang ada");
    }

    @Test
    @DisplayName("Test scenario untuk method addCoin(integer) dan jumlah uang coin tiap nominalnya")
    public void testAddCoin() {
        myWallet.addCoin(1000);
        myWallet.addCoin(500);
        myWallet.addCoin(200);
        myWallet.addCoin(1100);

        assertEquals(1, myWallet.getCoins().get(1000).intValue());
        assertEquals(1, myWallet.getCoins().get(500).intValue());
        assertEquals(1, myWallet.getCoins().get(200).intValue());
        assertNull(myWallet.getCoins().get(1100), "Invalid money type should not be added");
    }

    @Test
    @DisplayName("Test scenario untuk method takeMoneys(integer)")
    public void testTakeMoneys() {
        myWallet.addMoney(1000);
        myWallet.addMoney(100000);

        // memastikan jumlah uang kertas awal sesuai
        assertEquals(1, myWallet.getMoneys().get(1000));
        assertEquals(1, myWallet.getMoneys().get(100000));

        // memanggil metode takeMoneys
        myWallet.takeMoneys(1000);
        myWallet.takeMoneys(100000);

        // memastikan uang kertas telah diambil dengan benar
        assertEquals(0, myWallet.getMoneys().get(1000));
        assertEquals(0, myWallet.getMoneys().get(100000));

        // test mengambil lebih banyak uang kertas daripada yang tersedia
        assertThrows(IllegalArgumentException.class, () -> myWallet.takeMoneys(1000));
        assertThrows(IllegalArgumentException.class, () -> myWallet.takeMoneys(100000));
    }

    @Test
    @DisplayName("Test scenario untuk method takeCoin(integer)")
    public void testTakeCoins() {
        myWallet.addCoin(200);
        myWallet.addCoin(1000);

        // memastikan jumlah koin awal sesuai
        assertEquals(1, myWallet.getCoins().get(200));
        assertEquals(1, myWallet.getCoins().get(1000));

        // memanggil metode takeCoins
        myWallet.takeCoins(200);
        myWallet.takeCoins(1000);

        // memastikan koin telah diambil dengan benar
        assertEquals(0, myWallet.getCoins().get(200));
        assertEquals(0, myWallet.getCoins().get(1000));

        // test mengambil lebih banyak koin daripada yang tersedia
        assertThrows(IllegalArgumentException.class, () -> myWallet.takeCoins(200));
        assertThrows(IllegalArgumentException.class, () -> myWallet.takeCoins(1000));
    }

    @Test
    @DisplayName("Test scenario untuk menghitung jumlah uang kertas")
    public void testCalculateMoneys() {
        Wallet wallet = new Wallet("John Doe");
        wallet.addMoney(1000);
        wallet.addMoney(5000);
        wallet.addMoney(10000);
        assertEquals(1000 + 5000 + 10000, wallet.calculateMoneys());
    }

    @Test
    @DisplayName("Test scenario untuk menghitung jumlah koin")
    public void testCalculateCoins() {
        Wallet wallet = new Wallet("John Doe");
        wallet.addCoin(200);
        wallet.addCoin(500);
        wallet.addCoin(1000);
        assertEquals(200 + 500 + 1000, wallet.calculateCoins());
    }

    @Test
    @DisplayName("Test scenario untuk fungsionalias penghitungan jumlah uang dalam dompet")
    public void testGetMoneyAvailable() {
        Wallet myWallet = new Wallet("John Doe");
        assertEquals(0, myWallet.getMoneyAvailable());

        myWallet.addMoney(10000);
        myWallet.addCoin(200);
        assertEquals(10200, myWallet.getMoneyAvailable(), "After Adding Money and Coin, Money Available should be the sum");
    }
}


