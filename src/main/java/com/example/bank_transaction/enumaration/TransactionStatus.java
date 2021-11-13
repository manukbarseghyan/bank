package com.example.bank_transaction.enumaration;

public enum TransactionStatus {
    PENDING(1) {
        public String toString() {
            return "id: 1, status: pending";
        }
    },

    ACCEPTED(2) {
        public String toString() {
            return "id: 2, status: accepted";
        }
    },

    CANCELED(3) {
        public String toString() {
            return "id: 3, status: canceled";
        }
    };


    private int id;

    TransactionStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static TransactionStatus getById(int id) {
        for (TransactionStatus type : values()) {
            if (type.id == (id))
                return type;
        }

        return null;
    }

}
