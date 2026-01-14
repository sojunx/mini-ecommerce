import type { Product } from "@/lib/types";

export const mockProducts: {
  data: { products: Product[] };
  message: string;
  success: boolean;
} = {
  data: {
    products: [
      {
        category: "CLOTHING",
        created_at: "2026-01-14T13:38:09.435163",
        description: "A comfortable 100% cotton white t-shirt.",
        id: "49949b84-8952-41f0-83fa-11fc8a3d80b8",
        name: "Classic White T-Shirt",
        price: 19.99,
        sku: "SKU-001",
        updated_at: "2026-01-14T13:38:09.435163",
      },
      {
        category: "ELECTRONICS",
        created_at: "2026-01-14T13:38:09.435163",
        description: "Noise-canceling over-ear wireless headphones.",
        id: "75281f16-63f7-4b20-9996-7fca8b1bedfa",
        name: "Wireless Headphones",
        price: 149.5,
        sku: "SKU-002",
        updated_at: "2026-01-14T13:38:09.435163",
      },
      {
        category: "ELECTRONICS",
        created_at: "2026-01-14T13:38:09.435163",
        description: "RGB backlit mechanical keyboard with blue switches.",
        id: "38ce3d06-1ba1-4549-bec6-698d404bbef1",
        name: "Mechanical Keyboard",
        price: 89.0,
        sku: "SKU-003",
        updated_at: "2026-01-14T13:38:09.435163",
      },
      {
        category: "ACCESSORIES",
        created_at: "2026-01-14T13:38:09.435163",
        description: "Handcrafted genuine leather wallet in brown.",
        id: "47141f3f-8875-4abc-9200-278260d99b6e",
        name: "Leather Wallet",
        price: 45.0,
        sku: "SKU-004",
        updated_at: "2026-01-14T13:38:09.435163",
      },
      {
        category: "SPORTS",
        created_at: "2026-01-14T13:38:09.435163",
        description: "Lightweight and breathable shoes for daily running.",
        id: "77e24f90-bc81-4996-a4bb-00e0f84bbb4f",
        name: "Running Shoes",
        price: 120.0,
        sku: "SKU-005",
        updated_at: "2026-01-14T13:38:09.435163",
      },
    ],
  },
  message: "Fetched products successfully",
  success: true,
};
