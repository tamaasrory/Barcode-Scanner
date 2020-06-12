CREATE TABLE `penjualan` (
	`id_penjualan`	INTEGER NOT NULL,
	`tgl_penjualan`	TEXT NOT NULL,
	PRIMARY KEY(id_penjualan)
);

CREATE TABLE `kategori` (
	`id_kategori`	INTEGER NOT NULL,
	`nama_kategori`	TEXT NOT NULL,
	PRIMARY KEY(id_kategori)
);

CREATE TABLE `detail_penjualan` (
	`id_penjualan`	INTEGER NOT NULL,
	`barang`	INTEGER NOT NULL,
	`harga_barang`	NUMERIC NOT NULL,
	`qty`	INTEGER NOT NULL
);

CREATE TABLE `barang` (
	`id_barang`	INTEGER NOT NULL,
	`nama_barang`	TEXT NOT NULL,
	`kode_barang`	TEXT NOT NULL,
	`harga_beli`	NUMERIC NOT NULL DEFAULT 0,
	`harga_jual`	NUMERIC NOT NULL DEFAULT 0,
	`stok`	INTEGER NOT NULL DEFAULT 0,
	`kategori`	INTEGER NOT NULL,
	`exp`	TEXT NOT NULL,
	PRIMARY KEY(id_barang)
);
