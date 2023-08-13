import React from "react";

const CardVideoSection = () => {
  return (
    <div className="mt-32">
      <h1 className="text-5xl font-bold my-10 text-center">
        India's most ambition car
      </h1>

      <div>
        <video
          autoPlay
          loop
          controls
          style={{ width: "100%", height: "100%" }}
          src="https://res.cloudinary.com/dnbw04gbs/video/upload/v1686548750/pexels-pavel-danilyuk-6157781-1280x720-30fps_ulbgjb.mp4"
        />
      </div>
    </div>
  );
};

export default CardVideoSection;
