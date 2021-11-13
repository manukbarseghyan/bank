package com.example.bank_transaction.enumaration;

public enum Role {
    USER(1) {
        public String toString() {
            return "id: 1, role: user";
        }
    },

    ADMIN(2) {
        public String toString() {
            return "id: 2, role: admin";
        }
    };
    private final int id;

    Role(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Role getById(int id) {
        for (Role type : values()) {
            if (type.id == (id))
                return type;
        }

        return null;
    }

}
