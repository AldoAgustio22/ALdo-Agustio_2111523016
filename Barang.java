import java.util.ArrayList;
import java.util.Scanner;

// Aldo Agustio NIM : 2111523016

public class Barang {
	ArrayList<Barang> listBarang = new ArrayList<Barang>();
	ArrayList<String> listMember = new ArrayList<String>();
	Scanner in = new Scanner(System.in);
	
	private String name;
	private String transaction;
	private int quantity;
	private float price;
	
	public Barang() {
		
	}
	
	public Barang(String name, String transaction, int quantity, float price) {
		this.name = name;
		this.transaction = transaction;
		this.quantity = quantity;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void printList() {
		System.out.println("+====+==============================+==========================+=======+===============+");
		System.out.println("|    |                              |                          |       |               |");
		System.out.println("| No |             Name             |        Transaction       |  Qty  |     Price     |");
		System.out.println("|    |                              |                          |       |               |");
		System.out.println("+====+==============================+==========================+=======+===============+");
		for(int i = 0; i < listBarang.size(); i++) {
			System.out.printf("| %-2d | %-28s | %-24s | %-5d | %-13f |\n", (i+1), listBarang.get(i).getName(), 
					listBarang.get(i).getTransaction(), listBarang.get(i).getQuantity(), listBarang.get(i).getPrice());
			System.out.println("+====+==============================+==========================+=======+===============+");
		}
	}
	
	public void printMenu() {
		for(int i=0; i<25; i++) System.out.println("");
		System.out.println("--------------- POS ----------------");
		System.out.println("1. Tambah Barang");
		System.out.println("2. Cari Barang");
		System.out.println("3. Transaksi Pembelian");
		System.out.println("4. Transaksi Penjualan");
		System.out.println("5. Member");
		System.out.println("6. Laporan");
		System.out.println("7. Keluar");
	}
	
	public void menu1(Barang barang) {
		barang = new Barang();
		
		String name;
		String transaction;
		int quantity;
		float price;
		
		System.out.println("Masukkan nama barang : ");
		name = in.nextLine();
		
		do {
			System.out.println("Masukkan transaksi [Pembelian | Penjualan] : ");
			transaction = in.nextLine();
		} while(!transaction.equals("Pembelian") && !transaction.equals("Penjualan"));
		
		System.out.println("Masukkan kuantitas : ");
		quantity = in.nextInt();
		
		System.out.println("Masukkan harga : ");
		price = in.nextFloat();
		
		barang.setName(name);
		barang.setTransaction(transaction);
		barang.setQuantity(quantity);
		barang.setPrice(price);
		
		listBarang.add(barang);
		
		System.out.println("Barang berhasil ditambahkan!");
		in.nextLine();
		in.nextLine();
	}
	
	public void menu2() {	
		if(listBarang.isEmpty()) {
			System.out.println("Daftar Barang Kosong! Silakan Masukkan Barang");
		} else {
			printList();
			
			String name;
			int flag = 0;
			
			System.out.println("Masukkan nama barang yang ingin dicari : ");
			name = in.nextLine();
			
			for(int i = 0; i < listBarang.size(); i++) {
				if(name.equals(listBarang.get(i).getName())) {
					System.out.println(listBarang.get(i).getName() + " ditemukan pada index ke - " + (i+1));
					flag = 1;
					break;
				}
			}
			
			if(flag == 0) System.out.println("Barang tidak ditemukan");
		}

		in.nextLine();
	}
	
	public void menu3() {
		if(listBarang.isEmpty()) {
			System.out.println("Daftar Barang Kosong! Silakan Masukkan Barang!");
		} else {
			printList();
			
			int index;
			
			do {
				System.out.println("Masukkan index barang yang ingin dibeli [1 - " + listBarang.size() + "] : ");
				index = in.nextInt();
			} while(index < 0 || index > listBarang.size());
			
			listBarang.get(index - 1).setQuantity(listBarang.get(index - 1).getQuantity() + 1);
		}
		System.out.println("Barang berhasil dibeli!");
		in.nextLine();
		in.nextLine();
	}
	
	public void menu4() {
		if(listBarang.isEmpty()) {
			System.out.println("Daftar Barang Kosong! Silakan Masukkan Barang!");
		} else {
			printList();
			
			int index;
			
			do {
				System.out.println("Masukkan index barang yang ingin dijual : ");
				index = in.nextInt();
			} while(index < 0 || index > listBarang.size());
			
			if(listBarang.get(index - 1).getQuantity() - 1 < 1) {
				listBarang.remove(index - 1);
			} else {
				listBarang.get(index - 1).setQuantity(listBarang.get(index - 1).getQuantity() - 1);
			}
		}
		System.out.println("Barang berhasil dijual!");
		in.nextLine();
		in.nextLine();
	}
	
	public void menu5() {
		listMember.add("Aldo");
		listMember.add("Agustio");
		
		if(listMember.isEmpty()) {
			System.out.println("Daftar Member Kosong! Silakan Masukkan Member!");
		} else {
			System.out.println("Daftar Member");
			System.out.println("=============");
			for(int i = 0; i < listMember.size(); i++) {
				System.out.println((i+1) + ". " + listMember.get(i));
			}
		}
		in.nextLine();
	}
	
	public void menu6() {
		if(listBarang.isEmpty()) {
			System.out.println("Daftar Barang Kosong! Silakan Masukkan Barang!");
		} else {
			printList();
			
			int countPembelian = 0;
			int countPenjualan = 0;
			
			for(int i = 0; i < listBarang.size(); i++) {
				if("Pembelian".equals(listBarang.get(i).getTransaction())) countPembelian++;
				if("Penjualan".equals(listBarang.get(i).getTransaction())) countPenjualan++;
			}
			
			System.out.println();
			System.out.println("Laporan Pembelian dan Penjualan");
			System.out.println("===============================");
			System.out.println("Total Pembelian Barang: " + countPembelian);
			System.out.println("Total Penjualan Barang: " + countPenjualan);
		}
		in.nextLine();
		in.nextLine();
	}
	
	public static void main(String[] args) {
		Barang barang = new Barang();
		
		int choice;
		Scanner in = new Scanner(System.in);
		String username;
        String password;

        do {
           System.out.println("Masukkan username: ");
           username = in.nextLine();
		} while(!username.equals("aldo"));

        do {
           System.out.println("Masukkan password: ");
           password = in.nextLine();
		} while(!password.equals("aldo123"));
		
		do {
			barang.printMenu();
			
			do {
				System.out.println(">> ");
				choice = in.nextInt();
			} while(choice < 1 || choice > 7);
			
			switch(choice) {
				case 1:
					barang.menu1(barang);
					break;
				
				case 2:
					barang.menu2();
					break;
					
				case 3:
					barang.menu3();
					break;
					
				case 4:
					barang.menu4();
					break;
					
				case 5:
					barang.menu5();
					break;
					
				case 6:
					barang.menu6();
					break;
			}
		} while (choice != 7); 
		
		in.close();

	}

}