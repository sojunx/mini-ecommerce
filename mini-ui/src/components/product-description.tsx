const ProductDescription = ({ description }: { description: string }) => {
  return (
    <div className="bg-muted outline rounded-lg p-4 min-h-32">
      <h1 className="font-medium">Description</h1>
      <p>{description}</p>
    </div>
  );
};
export default ProductDescription;
