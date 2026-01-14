import { useParams } from "react-router";

const ProductDetails = () => {
  const params = useParams();

  return <div>{params.id}</div>;
};

export default ProductDetails;
