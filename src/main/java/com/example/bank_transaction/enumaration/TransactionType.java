package com.example.bank_transaction.enumaration;

public enum TransactionType {
    DEPOSIT(1) {
        public String toString() {
            return "id: 1, type: DEPOSIT";
        }
    },

    WITHDRAWAL(2) {
        public String toString() {
            return "id: 2, type: WITHDRAWAL";
        }
    };
    private int id;

    TransactionType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static TransactionType getById(int id) {
        for (TransactionType type : values()) {
            if (type.id == (id))
                return type;
        }

        return null;
    }

}
