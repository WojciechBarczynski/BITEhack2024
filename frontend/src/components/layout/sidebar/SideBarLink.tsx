import React, { ReactElement } from 'react';
import { Link } from 'react-router-dom';

type SideBarLinkProps = {
  icon: ReactElement,
  to: string,
  label: string
}

const SideBarLink: React.FC<SideBarLinkProps> = ({
  icon, to, label
}) => {
  return (
    <div className="sidebarLink">
      <div className="iconPositioner">
        {icon}
      </div>
      <Link to={to}>{label}</Link>
    </div>
  );
};

export default SideBarLink;