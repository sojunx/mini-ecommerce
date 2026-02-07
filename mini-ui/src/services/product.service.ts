import http from "@/lib/http";

const ProductService = {
  getAllProducts: async (page: number) => {
    try {
      const res = await http.get(`/api/products?page=${page}&size=15`);

      return res.data;
    } catch (error: unknown) {
      throw new Error((error as Error).message);
    }
  },

  getProductById: async (id: string) => {
    try {
      const res = await http.get(`/api/products/${id}`);

      return res.data;
    } catch (error: unknown) {
      throw new Error((error as Error).message);
    }
  },

  searchProducts: async (name: string) => {
    try {
      const res = await http.get(
        `/api/products/search?name=${encodeURIComponent(name)}`,
      );

      return res.data;
    } catch (error: unknown) {
      throw new Error((error as Error).message);
    }
  },
};

export default ProductService;
